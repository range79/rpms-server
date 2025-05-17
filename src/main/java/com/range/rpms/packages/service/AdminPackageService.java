package com.range.rpms.packages.service;

import com.range.rpms.packages.exception.PackageNotFoundException;

public interface AdminPackageService {
    void deleteAllPackages();
    void deleteOtherUserPackage(String packageName) throws PackageNotFoundException;


}
