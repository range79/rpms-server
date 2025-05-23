package com.range.rpms.packages.service;

import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.exception.PackageNotFoundException;

import java.util.List;

public interface AdminPackageService {
    void deleteAllPackages();
    void deleteOtherUserPackage(String packageName) throws PackageNotFoundException;
    List<PackageMetaData> getAllPackages();
}
