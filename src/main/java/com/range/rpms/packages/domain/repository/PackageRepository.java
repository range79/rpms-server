package com.range.rpms.packages.domain.repository;

import com.range.rpms.packages.domain.model.PackageEntity;
import com.range.rpms.packages.enums.PackageVisibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PackageRepository extends MongoRepository<PackageEntity, String> {


    Optional<PackageEntity> findByName(String packageName);
    void deleteByAuthor(Long id);
    void deleteByName(String name);
    List<PackageEntity> findByNameLike(String name);

    Page<PackageEntity> findByAuthor(Long id, Pageable pageable);

    List<PackageEntity> findAllByPackageVisibility(PackageVisibility packageVisibility);
}
