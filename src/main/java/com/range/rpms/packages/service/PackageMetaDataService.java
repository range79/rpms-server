package com.range.rpms.packages.service;

import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.exception.PackageNotFoundException;

import java.util.List;

public interface PackageMetaDataService {
    /**
     * Get all packages data from database
     * @return package metadata list
     */
    List<PackageMetaData> getAllPackages();

    /**
     * Searches for packages with the given name.
     *
     * @param packageName the name of the package to search for
     * @return a list of matching PackageMetaData objects
     * @throws PackageNotFoundException if no packages are found with the given name
     */
    List<PackageMetaData> searchPackage(String packageName) throws PackageNotFoundException;


}
