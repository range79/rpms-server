package com.range.rpms.repository.domain.repository

import com.range.rpms.repository.domain.entity.PackageEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PackageRepository: JpaRepository<PackageEntity, UUID> {
}