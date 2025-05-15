package com.range.rpms.service.impl;

import com.range.rpms.dao.model.PackageEntity;
import com.range.rpms.dao.repository.Packagerepository;
import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.dto.pkg.UploadPackageRequest;
import com.range.rpms.exception.pkg.*;
import com.range.rpms.mapper.PackageMapper;
import com.range.rpms.service.PackageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    private final Packagerepository packagerepository;
    private final PackageMapper packageMapper;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("tgz","deb","gz","zip","tar","bz2");

    public PackageServiceImpl(Packagerepository packagerepository, PackageMapper packageMapper) {
        this.packagerepository = packagerepository;
        this.packageMapper = packageMapper;
    }
    /**
     This service is responsible for updating package details, such as version or package author.
     Only the package author is allowed to make changes to the author information.
     Other updates, like version changes, can be made as needed.
     */
    @Override
    @Transactional
    public PackageMetaData addPackage(UploadPackageRequest uploadPackageRequest) throws PackageNotFoundException {

        if (isInValidExtension(uploadPackageRequest.getFile().getOriginalFilename())) {

            throw new UnsupportedFileException("this extension is not supported");

        }


        try{

            // Map uploadRequest to PackageEntity for saving to database
            PackageEntity packageEntity = packageMapper.toEntity(uploadPackageRequest);
            String fileExtension = getFileExtension(uploadPackageRequest.getFile().getOriginalFilename());
            packageEntity.setName(uploadPackageRequest.getName()+fileExtension);
            //save to database
            packagerepository.save(packageEntity);
            // return dto to avoid sending full package content.
            return packageMapper.toPackageMetaData(packageEntity);

        }
        catch (Exception e){
            //if failed saving to database send an exception
            throw new PackageCantUploadException("Package " + uploadPackageRequest.getName() + " cannot be added");

        }

    }
    /**
     * This method is responsible for downloading package files.
     * It can be accessed via
     * the /package/{packageName} endpoint.
     * Usage (on the Range Package Manager client):
     * rpmc download {packageName}
     */
    @Transactional
    @Override
    public PackageMetaData updatePackage(String id, UploadPackageRequest uploadPackageRequest) throws PackageNotFoundException, IOException {

        //find package in database if package not find in database throw an exception
        //if package content null or empty throw an exception

        PackageEntity packageEntity = packagerepository.findById(id)
                .orElseThrow(()->new PackageNotFoundException("Package "+id+" not found"));

        if (isInValidExtension(uploadPackageRequest.getFile().getOriginalFilename())) {
            throw new UnsupportedFileException( "this extension is not supported");
        }
        if (packageEntity.getVersion()>= uploadPackageRequest.getVersion()){
            throw new CantDownGradePackageException("can't downgrade package with version " + uploadPackageRequest.getVersion());
        }
        String fileExtension= getFileExtension(uploadPackageRequest.getFile().getOriginalFilename());
        packageEntity.setName(uploadPackageRequest.getName()+fileExtension);
        packageEntity.setVersion(uploadPackageRequest.getVersion());
        packageEntity.setDescription(uploadPackageRequest.getDescription());
        packageEntity.setContent(uploadPackageRequest.getFile().getBytes());
        return packageMapper.toPackageMetaData(packagerepository.save(packageEntity));

    }
    /**
     * Method for downloading packages
     * by ID in the Range Package Manager.
     * This handles retrieving and
     * serving package files to clients.
     */

    @Override
    public ByteArrayResource downloadPackageById(String packageId) throws PackageNotFoundException {
        //find package by id or else Throw an exception
        PackageEntity packageEntity = packagerepository.findById(packageId)
                .orElseThrow(()->new PackageNotFoundException("Package with "+packageId+" not found"));
        // if package content null or empty throw an exception
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
    @Override
    @Transactional
    public void deletePackage(String packageName) throws PackageNotFoundException {
        // Find package or throw if not found
        packagerepository.findByName(packageName).orElseThrow
                (()->new PackageDeletionException("Package "+packageName+" not found"));

        packagerepository.deleteByName(packageName);
    }
    @Override
    public void deleteAllPackages() {
        if (packagerepository.findAll().isEmpty()){
            throw new PackageDeletionException("Packages not found");}
        packagerepository.deleteAll();
    }

    private boolean isInValidExtension(String packageName){
           /*
           Get File original name and if its
           null, or it has no extension throw an exception
         */
        if (packageName == null
                || !packageName.contains(".")){

            throw new PackageCantUploadException("wrong format file");

        }


        // get file extension from file
        String fileExtension = packageName.substring(packageName.lastIndexOf(".") + 1).toLowerCase();
        //if file extension contains allowed extension return true else false
        return !ALLOWED_EXTENSIONS.contains(fileExtension);
    }
    public String getFileExtension(String packageName){
        return packageName
                .substring(packageName
                        .lastIndexOf("."));
    }


}
