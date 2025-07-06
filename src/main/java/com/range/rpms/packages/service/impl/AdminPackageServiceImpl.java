package com.range.rpms.packages.service.impl;

import com.range.rpms.packages.domain.repository.PackageRepository;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.exception.PackageDeletionException;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.mapper.PackageMapper;
import com.range.rpms.packages.service.AdminPackageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminPackageServiceImpl implements AdminPackageService {
    private PackageRepository packagerepository;
    private final PackageMapper packageMapper;
    public AdminPackageServiceImpl(PackageRepository packagerepository,
                                   PackageMapper packageMapper) {
        this.packagerepository = packagerepository;
        this.packageMapper = packageMapper;

    }
    @Override
    public void deleteAllPackages() {
        if (packagerepository.findAll().isEmpty()){
            throw new PackageDeletionException("Packages not found");}
        packagerepository.deleteAll();
    }


    @Override
    public void deleteOtherUserPackage(String packageName) throws PackageNotFoundException {
        packagerepository.findByName(packageName).orElseThrow
                (()->new PackageDeletionException("Package "+packageName+" not found"));

        packagerepository.deleteByName(packageName);
    }
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
}
