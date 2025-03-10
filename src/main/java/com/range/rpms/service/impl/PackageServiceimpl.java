package com.range.rpms.service.impl;

import com.range.rpms.dao.model.PackageEntity;
import com.range.rpms.dao.repository.Packagerepo;
import com.range.rpms.dto.PackageDto;
import com.range.rpms.exception.PackageNotFoundException;
import com.range.rpms.mapper.PackageMapper;
import com.range.rpms.service.PackageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageServiceimpl implements PackageService {

    private final Packagerepo packagerepo;
    private final PackageMapper packageMapper;
    public PackageServiceimpl(Packagerepo packagerepo,PackageMapper packageMapper) {
        this.packagerepo = packagerepo;
        this.packageMapper = packageMapper;
    }
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
    public PackageEntity searchPackage(String packageName) throws PackageNotFoundException {
        return  packagerepo.findByName(packageName)
                .orElseThrow(()-> new PackageNotFoundException("Package " + packageName + " not found"));
    }
    /*
    This service is responsible for updating package details, such as version or package author.
    Only the package author is allowed to make changes to the author information.
    Other updates, like version changes, can be made as needed.
        */
    public PackageDto addPackage(MultipartFile file, String name, String description) throws PackageNotFoundException, IOException {
      try{
          PackageEntity packageEntity = new PackageEntity();
          packageEntity.setName(name);
          packageEntity.setDescription(description);
          packageEntity.setContent(file.getBytes());
          packagerepo.save(packageEntity);
          return packageMapper.toPackageDto(packageEntity);

      }catch (Exception e){
          throw new PackageNotFoundException("Package " + name + " cannot be added");
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
     * This service is responsible for deleting packages by name.
     * It is accessed via the /package/delete/{packageName} endpoint.
     * Only admins are authorized to use this service.
     */
    public void deletePackage(String packageName) throws PackageNotFoundException {
        packagerepo.deleteByName(packageName);
    }
}
