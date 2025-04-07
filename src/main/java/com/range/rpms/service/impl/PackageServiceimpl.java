package com.range.rpms.service.impl;

import com.range.rpms.dao.model.Dependencies;
import com.range.rpms.dao.model.PackageEntity;
import com.range.rpms.dao.repository.Packagerepo;
import com.range.rpms.dto.PackageDto;
import com.range.rpms.exception.PackageCantUploadException;
import com.range.rpms.exception.PackageNotFoundException;
import com.range.rpms.mapper.PackageMapper;
import com.range.rpms.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class PackageServiceimpl implements PackageService {

    private final Packagerepo packagerepo;
    private final PackageMapper packageMapper;

    /*
     * This service is for server administrators.
     * Instead of opening Cassandra and manually querying the database,
     * administrators can use the /package/all endpoint to list packages.
     */
    public List<PackageDto> getAllPackages() {
        return packagerepo.findAll().stream().map(packageMapper::toPackageDto).collect(Collectors.toList());
    }
    /*
     * Searches for a package in the database by name.
     * This service is accessed via the /package/search/{packagename} endpoint.
     *
     * Usage (on the Range package manager client): rpmc search {package_name}
     */
    public List<PackageDto> searchPackage(String packageName) throws PackageNotFoundException {




        return  packagerepo.findByNameLike(packageName).stream().map(packageMapper::toPackageDto).limit(5).collect(Collectors.toList());
    }
    /*
    This service is responsible for updating package details, such as version or package author.
    Only the package author is allowed to make changes to the author information.
    Other updates, like version changes, can be made as needed.
        */
    public PackageDto addPackage(MultipartFile file, String name, String description, String version, Dependencies[] dependencies) throws PackageNotFoundException, IOException {
        if (file.getOriginalFilename() == null || !file.getOriginalFilename().contains(".")){
            throw new PackageCantUploadException("wrong format file");
        }
        List<String> allowedExtensions = Arrays.asList("tgz","deb","gz","zip","tar","bz2");
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!allowedExtensions.contains(fileExtension)) {
            throw new PackageCantUploadException("can't upload file with extension " + fileExtension);
        }
        try{
            PackageEntity packageEntity = new PackageEntity();
            packageEntity.setName(name);
            packageEntity.setDescription(description);
            packageEntity.setVersion(version);
            packageEntity.setContent(file.getBytes());
            packageEntity.setDependencies(dependencies);
            packagerepo.save(packageEntity);
            return packageMapper.toPackageDto(packageEntity);

        }
        catch (Exception e){
            throw new PackageCantUploadException("Package " + name + " cannot be added");
        }

    }
    /*
     * This service is responsible for downloading package files.
     * It can be accessed via the /package/download/{packageName} endpoint.
     *
     * Usage (on the Range Package Manager client): rpmc download {packageName}
     */

    public ByteArrayResource DownloadPackageByName(String packageName) throws PackageNotFoundException {
        PackageEntity packageEntity = packagerepo.findByName(packageName)
                .orElseThrow(()->new PackageNotFoundException("Package "+packageName+" not found"));

        if (packageEntity.getContent() == null || packageEntity.getContent().length == 0) {
            throw new PackageNotFoundException("Package " + packageName + " does not contain any content.");
        }
        return new ByteArrayResource(packageEntity.getContent());
    }
    /*
     * Service for downloading packages by ID in the Range Package Manager.
     * This handles retrieving and serving package files to clients.
     */
    @Override
    public ByteArrayResource DownloadPackageById(String packageId) throws PackageNotFoundException {
        PackageEntity packageEntity = packagerepo.findById(packageId)
                .orElseThrow(()->new PackageNotFoundException("Package with "+packageId+" not found"));
        if (packageEntity.getContent() == null || packageEntity.getContent().length == 0) {
            throw new PackageNotFoundException("Package with "+packageId+" does not contain any content.");
        }
        return new ByteArrayResource(packageEntity.getContent());
    }

    /*
     * This service is responsible for deleting packages by name.
     * It is accessed via the /package/delete/{packageName} endpoint.
     * Only admins are authorized to use this service.
     */
    public void deletePackage(String packageName) throws PackageNotFoundException {
        packagerepo.deleteByName(packageName);
    }
}
