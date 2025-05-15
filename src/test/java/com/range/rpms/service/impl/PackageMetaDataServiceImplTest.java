package com.range.rpms.service.impl;

import com.range.rpms.dao.model.PackageEntity;
import com.range.rpms.dao.repository.Packagerepo;
import com.range.rpms.mapper.PackageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.List;

class PackageMetaDataServiceImplTest {

    private Packagerepo packagerepo;
    private PackageMetaDataServiceImpl packageMetaDataService;
    private PackageEntity packageEntity;
    private PackageEntity packageEntity1;
    private PackageMapper packageMapper;

    @BeforeEach
    void setUp() {
        packagerepo= Mockito.mock(Packagerepo.class);
        packageMapper = Mappers.getMapper(PackageMapper.class);
        packageMetaDataService = new PackageMetaDataServiceImpl(packagerepo,packageMapper);
        packageEntity = new PackageEntity("1", "merhaba", "desc1", "azad", 1.0, new byte[0]);
        packageEntity1 = new PackageEntity("2", "merhaba-2", "desc2", "range", 2.0, new byte[0]);
    }

    @Test
    void getAllPackages() {
        Mockito.when(packagerepo.findAll()).thenReturn(List.of(packageEntity, packageEntity1));

    }

    @Test
    void searchPackage() {
    }
}