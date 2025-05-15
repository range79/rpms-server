package com.range.rpms.mapper;

import com.range.rpms.dao.model.PackageEntity;
import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.dto.pkg.UploadPackageRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",imports = {com.range.rpms.mapper.MapperUtil.class})
public interface PackageMapper {

    PackageMetaData toPackageMetaData(PackageEntity packageEntity);
    @Mapping(target = "content", expression = "java(MapperUtil.getFileBytes(request))")
    PackageEntity toEntity(UploadPackageRequest request) ;

}