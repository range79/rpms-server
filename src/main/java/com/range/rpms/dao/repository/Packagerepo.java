package com.range.rpms.dao.repository;

import com.range.rpms.dao.model.PackageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface Packagerepo extends MongoRepository<PackageEntity, String> {


    Optional<PackageEntity> findByName(String packageName);

    void deleteByName(String name);
}
