package com.range.rpms.packages.mapper;

import com.range.rpms.packages.domain.model.PackageEntity;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",imports = {MapperUtil.class})
public interface PackageMapper {

    PackageMetaData toPackageMetaData(PackageEntity packageEntity);
    @Mapping(target = "content", expression = "java(MapperUtil.getFileBytes(request))")
    PackageEntity toEntity(UploadPackageRequest request) ;

}