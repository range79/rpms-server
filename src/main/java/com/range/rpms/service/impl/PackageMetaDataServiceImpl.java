package com.range.rpms.service.impl;

import com.range.rpms.dao.repository.Packagerepository;
import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.exception.pkg.PackageNotFoundException;
import com.range.rpms.mapper.PackageMapper;
import com.range.rpms.service.PackageMetaDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PackageMetaDataServiceImpl implements PackageMetaDataService {

    private final Packagerepository packagerepository;

    private final PackageMapper packageMapper;

    public PackageMetaDataServiceImpl(Packagerepository packagerepository, PackageMapper packageMapper) {

        this.packagerepository = packagerepository;
        this.packageMapper = packageMapper;

    }
    /**
     * This service is for server administrators.
     * Instead of opening Cassandra and manually querying the database,
     * administrators can use the /search/all endpoint to list packages.
     */
    @Override
    public List<PackageMetaData> getAllPackages() {
        //find all packages from db
        return packagerepository
                //findAll
                .findAll()
                .stream()
                /*
                 * Use DTO to avoid sending full package content.
                 * Returning all package data including file content could lead to memory overload.
                 */
                .map(packageMapper::toPackageMetaData)
                .collect(Collectors.toList());
    }
    /**
     * Searches for a package in the database by name.
     * This service is accessed via the search/{packageName} endpoint.
     * Usage (on the Range package manager client): rpmc search {package_name}
     */
    @Override
    public List<PackageMetaData> searchPackage(String packageName) throws PackageNotFoundException {

        List<PackageMetaData> metaDataList=   packagerepository
                .findByNameLike(packageName)
                .stream()
                /*
                 * Use DTO to avoid sending full package content.
                 * Returning all package data including file content could lead to memory overload.
                 */
                .map(packageMapper::toPackageMetaData)

                .collect(Collectors.toList());
        //if metadataList is empty throw an exception
        if (metaDataList.isEmpty()){

            throw new PackageNotFoundException("No package found with name: " +packageName);

        }return metaDataList;
    }
}
