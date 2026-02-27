package com.range.rpms.repository.service

import com.range.rpms.repository.crypto.SshFingerprint
import com.range.rpms.repository.domain.entity.SshKeyEntity
import com.range.rpms.repository.domain.repository.SshKeyRepository
import com.range.rpms.repository.dto.AddSshKeyRequest
import com.range.rpms.repository.dto.SshKeyResponse
import com.range.rpms.repository.exception.DuplicateSshKeyException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import java.util.*

interface SshKeyService {

    fun addKey(userId: UUID, req: AddSshKeyRequest): SshKeyResponse

    fun listActiveKeys(userId: UUID, pageable: Pageable): Page<SshKeyResponse>

    fun revoke(keyId: UUID, userId: UUID)

    fun resolveUserByFingerprint(fingerprint: String): UUID?
}