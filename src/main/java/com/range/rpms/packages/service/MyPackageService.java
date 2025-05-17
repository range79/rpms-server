package com.range.rpms.packages.service;

import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.exception.PackageNotFoundException;

import java.io.IOException;
import java.util.List;

public interface MyPackageService {

    void deleteMyAllPackages();
    void deletePackageById(String id) throws PackageNotFoundException;
    PackageMetaData getPackageById(String packageId) throws PackageNotFoundException;
    List<PackageMetaData> getAllPackages() throws PackageNotFoundException;
    PackageMetaData updatePackage(String id , UploadPackageRequest uploadPackageRequest) throws PackageNotFoundException, IOException;

}
