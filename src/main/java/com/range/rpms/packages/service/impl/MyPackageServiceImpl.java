package com.range.rpms.packages.service.impl;

import com.range.rpms.common.util.FileExtensionUtil;
import com.range.rpms.common.util.SpringSecurityUserContextUtil;
import com.range.rpms.packages.dao.model.PackageEntity;
import com.range.rpms.packages.dao.repository.PackageRepository;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.exception.CantDownGradePackageException;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.exception.UnsupportedFileException;
import com.range.rpms.packages.exception.UserNotOwnerOfPackageException;
import com.range.rpms.packages.mapper.PackageMapper;
import com.range.rpms.packages.service.MyPackageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MyPackageServiceImpl implements MyPackageService {
    private PackageRepository packageRepository;
    private SpringSecurityUserContextUtil springSecurityUserContextUtil;
    private PackageMapper packageMapper;
    public MyPackageServiceImpl(PackageRepository packageRepository,
                                SpringSecurityUserContextUtil springSecurityUserContextUtil,
                                PackageMapper packageMapper ) {
        this.packageRepository = packageRepository;
        this.springSecurityUserContextUtil = springSecurityUserContextUtil;
        this.packageMapper = packageMapper;
    }


    @Override
    public void deleteMyAllPackages() {

    }

    @Override
    public void deletePackageById(String id) throws PackageNotFoundException {
        String currentUserName = springSecurityUserContextUtil.getCurrentUserName();

        packageRepository.deleteByAuthor(currentUserName);
    }

    @Override
    public PackageMetaData getPackageById(String packageId) throws PackageNotFoundException {
        return null;
    }

    @Override
    public List<PackageMetaData> getAllPackages() {
        String currentUserName = springSecurityUserContextUtil.getCurrentUserName();
        List<PackageEntity> entities = packageRepository.findByAuthor(currentUserName);

        return  entities.stream()
                .map(packageMapper::toPackageMetaData)
                .collect(Collectors.toList());


    }

    @Transactional
    @Override
    public PackageMetaData updatePackage(String id, UploadPackageRequest uploadPackageRequest) throws PackageNotFoundException, IOException {

        //find package in database if package not find in database throw an exception
        //if package content null or empty throw an exception

        PackageEntity packageEntity = packageRepository.findById(id)
                .orElseThrow(()->new PackageNotFoundException("Package "+id+" not found"));

        //get User id which perform this method
        String holderId = springSecurityUserContextUtil.getCurrentUserName();


        if (!holderId.equals(packageEntity.getAuthor())){
            throw new UserNotOwnerOfPackageException("User not owner of package "+id);
        }


        if (FileExtensionUtil.isInValidExtension(uploadPackageRequest.getFile().getOriginalFilename())) {
            throw new UnsupportedFileException( "this extension is not supported");
        }
        if (packageEntity.getVersion()>= uploadPackageRequest.getVersion()){
            throw new CantDownGradePackageException("can't downgrade package with version " + uploadPackageRequest.getVersion());
        }


        String fileExtension= FileExtensionUtil.getFileExtension(uploadPackageRequest.getFile().getOriginalFilename());

        packageEntity.setName(uploadPackageRequest.getName()+fileExtension);
        packageEntity.setVersion(uploadPackageRequest.getVersion());
        packageEntity.setDescription(uploadPackageRequest.getDescription());
        packageEntity.setContent(uploadPackageRequest.getFile().getBytes());
        return packageMapper.toPackageMetaData(packageRepository.save(packageEntity));

    }
}
