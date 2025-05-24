package com.range.rpms.packages.dao.repository;

import com.mongodb.RequestContext;
import com.range.rpms.packages.dao.model.PackageEntity;
import com.range.rpms.packages.enums.PackageVisibility;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PackageRepository extends MongoRepository<PackageEntity, String> {


    Optional<PackageEntity> findByName(String packageName);
    void deleteByAuthor(String name);
    void deleteByName(String name);
    List<PackageEntity> findByNameLike(String name);

    List <PackageEntity> findByAuthor(String author);

   List<PackageEntity> findAllByPackageVisibility(PackageVisibility packageVisibility);
}
