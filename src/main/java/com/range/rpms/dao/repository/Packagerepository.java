package com.range.rpms.dao.repository;

import com.range.rpms.dao.model.PackageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface Packagerepository extends MongoRepository<PackageEntity, String> {


    Optional<PackageEntity> findByName(String packageName);

    void deleteByName(String name);
    List<PackageEntity> findByNameLike(String name);
}
