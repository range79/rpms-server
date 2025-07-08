package com.range.rpms.likes.domain.repository;

import com.range.rpms.likes.domain.entity.PackageLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageLikeRepository extends JpaRepository<PackageLike, Long> {


    Boolean existsByUserIdAndPackageId(Long userId,Long packageId);



    List<PackageLike> findByPackageId(Long packageId);
}
