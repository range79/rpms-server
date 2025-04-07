package com.range.rpms.mapper;

import com.range.rpms.dao.model.PackageEntity;
import com.range.rpms.dto.PackageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    PackageDto toPackageDto(PackageEntity packageEntity);
    PackageEntity toPackageEntity(PackageDto packageDto);
}

