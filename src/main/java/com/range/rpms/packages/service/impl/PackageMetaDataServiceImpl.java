package com.range.rpms.packages.service.impl;

import com.range.rpms.packages.dao.model.PackageEntity;
import com.range.rpms.packages.dao.repository.PackageRepository;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.mapper.PackageMapper;
import com.range.rpms.packages.service.PackageMetaDataService;
import com.range.rpms.common.util.SpringSecurityUserContextUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PackageMetaDataServiceImpl implements PackageMetaDataService {

    private final PackageRepository packagerepository;

    private final PackageMapper packageMapper;
    private final SpringSecurityUserContextUtil springSecurityUserContextUtil;

    public PackageMetaDataServiceImpl(PackageRepository packagerepository,
                                      PackageMapper packageMapper,
                                      SpringSecurityUserContextUtil springSecurityUserContextUtil
    ) {
        this.springSecurityUserContextUtil = springSecurityUserContextUtil;
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
