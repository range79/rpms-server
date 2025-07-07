package com.range.rpms.likes.domain.repository;

import com.range.rpms.likes.domain.entity.PackageLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageLikeRepository extends JpaRepository<PackageLike, Long> {
}
