package com.range.rpms.packages.service.impl;

import com.range.rpms.packages.domain.model.PackageEntity;
import com.range.rpms.packages.domain.repository.PackageRepository;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.exception.*;
import com.range.rpms.packages.mapper.PackageMapper;
import com.range.rpms.common.util.UserContextUtil;
import com.range.rpms.packages.service.PackageService;
import com.range.rpms.common.util.FileExtensionUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packagerepository;
    private final PackageMapper packageMapper;


    public PackageServiceImpl(PackageRepository packagerepository
            , PackageMapper packageMapper
            , UserContextUtil springSecurityUserContextUtil) {
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

        if (FileExtensionUtil.isInValidExtension(uploadPackageRequest.getFile().getOriginalFilename())) {

            throw new UnsupportedFileException("this extension is not supported");

        }

        try{
            // Map uploadRequest to PackageEntity for saving to database
            PackageEntity packageEntity = packageMapper.toEntity(uploadPackageRequest);

            String fileExtension = FileExtensionUtil.getFileExtension(uploadPackageRequest.getFile().getOriginalFilename());
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



}
