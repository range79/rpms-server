package com.range.rpms.packages.service;


import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.exception.PackageNotFoundException;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;

public interface PackageService {
        // Adds a new package to the repository
        PackageMetaData addPackage(UploadPackageRequest uploadPackageRequest) throws PackageNotFoundException, IOException;

        //Download the package content by its id
        ByteArrayResource downloadPackageById(String packageId) throws PackageNotFoundException;

}

