package com.range.rpms.repository.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PackageRepository: JpaRepository<Package, UUID> {
}