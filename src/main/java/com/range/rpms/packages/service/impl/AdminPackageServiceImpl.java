package com.range.rpms.packages.service.impl;

import com.range.rpms.packages.dao.repository.PackageRepository;
import com.range.rpms.packages.exception.PackageDeletionException;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.service.AdminPackageService;
import org.springframework.stereotype.Service;

@Service
public class AdminPackageServiceImpl implements AdminPackageService {
    private PackageRepository packagerepository;
    public AdminPackageServiceImpl(PackageRepository packagerepository) {
        this.packagerepository = packagerepository;

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
}
