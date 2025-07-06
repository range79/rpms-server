package com.range.rpms.packages.service.impl;

import com.range.rpms.common.util.FileExtensionUtil;
import com.range.rpms.common.util.UserContext;
import com.range.rpms.common.util.UserContextUtil;
import com.range.rpms.packages.domain.model.PackageEntity;
import com.range.rpms.packages.domain.repository.PackageRepository;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.enums.PackageVisibility;
import com.range.rpms.packages.exception.CantDownGradePackageException;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.exception.UnsupportedFileException;
import com.range.rpms.packages.exception.UserNotOwnerOfPackageException;
import com.range.rpms.packages.mapper.PackageMapper;
import com.range.rpms.packages.mapper.PackageVisibilityMapper;
import com.range.rpms.packages.service.MyPackageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class MyPackageServiceImpl implements MyPackageService {
    private final PackageRepository packageRepository;
    private final UserContext currentUser;
    private final PackageMapper packageMapper;
    public MyPackageServiceImpl(PackageRepository packageRepository,
                                UserContextUtil currentUser,
                                PackageMapper packageMapper ) {
        this.packageRepository = packageRepository;
        this.currentUser = currentUser;
        this.packageMapper = packageMapper;
    }

@Transactional
    @Override
    public void deleteMyAllPackages() {

        packageRepository.deleteByAuthor(currentUserId());
    }
@Transactional
    @Override
    public void deletePackageById(String id) throws PackageNotFoundException {
        Long currentUserId = currentUser.getCurrentUserId();

        packageRepository.deleteByAuthor(currentUserId);
    }



    @Override
    public List<PackageMetaData> getAllPackages() {
        List<PackageEntity> entities = packageRepository.findByAuthor(currentUserId());

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





        if (!currentUserId().equals(packageEntity.getAuthor())){
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

    @Override
    public void setPackageVisibility(String packageId, int visibilityCode) throws PackageNotFoundException {


        PackageEntity entity = packageRepository.findById(packageId)
                .orElseThrow(()->new PackageNotFoundException("Package "+packageId+" not found"));
        if (!(Objects.equals(entity.getAuthor(), currentUserId()))){
            throw new UserNotOwnerOfPackageException("User not owner of package "+packageId);
        }
        PackageVisibility visibility = PackageVisibilityMapper.getPackageVisibility(visibilityCode);

        entity.setPackageVisibility(visibility);
        packageRepository.save(entity);
    }
    private Long currentUserId(){
        return  currentUser.getCurrentUserId();
    }
}
