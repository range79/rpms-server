package com.range.rpms.service;

import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.exception.pkg.PackageNotFoundException;

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
