package com.range.rpms.service;


import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.dto.pkg.UploadPackageRequest;
import com.range.rpms.exception.pkg.PackageNotFoundException;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;

public interface PackageService {
        // Adds a new package to the repository
        PackageMetaData addPackage(UploadPackageRequest uploadPackageRequest) throws PackageNotFoundException, IOException;

        // Downloads the package content by its name
        PackageMetaData updatePackage(String id , UploadPackageRequest uploadPackageRequest) throws PackageNotFoundException, IOException;

        //Download the package content by its id
        ByteArrayResource downloadPackageById(String packageId) throws PackageNotFoundException;

        // Deletes a package by its name
        void deletePackage(String packageName) throws PackageNotFoundException;
        void deleteAllPackages();
}

