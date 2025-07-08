package com.range.rpms.likes.domain.repository;

import com.range.rpms.likes.domain.entity.PackageLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageLikeRepository extends JpaRepository<PackageLike, Long> {
    List<PackageLike> findByUserIdAndPackageId(Long userId, Long packageId);

    Boolean exitsByUserIdAndPackageId(Long userId,Long packageId);

    List<PackageLike> findByUserId(Long userId);

    List<PackageLike> findByPackageId(Long packageId);
}
