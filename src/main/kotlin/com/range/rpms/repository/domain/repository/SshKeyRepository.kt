package com.range.rpms.repository.domain.repository

import com.range.rpms.repository.domain.entity.SshKeyEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SshKeyRepository : JpaRepository<SshKeyEntity, UUID> {
    fun existsByUserIdAndFingerprint(userId: UUID, fingerprint: String): Boolean
    fun findByFingerprintAndRevokedFalse(fingerprint: String): SshKeyEntity?
    fun findAllByUserIdAndRevokedFalse(userId: UUID, pageable: Pageable): Page<SshKeyEntity>
}