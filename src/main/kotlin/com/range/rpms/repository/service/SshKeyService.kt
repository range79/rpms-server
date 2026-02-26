package com.range.rpms.repository.service

import com.range.rpms.repository.domain.entity.SshKeyEntity
import com.range.rpms.repository.dto.AddSshKeyRequest
import com.range.rpms.repository.dto.SshKeyResponse
import com.range.rpms.repository.exception.DuplicateSshKeyException
import org.hibernate.query.Page
import org.springframework.data.domain.Pageable
import com.range.rpms.repository.domain.repository.SshKeyRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class SshKeyService(
    private val sshKeyRepository: SshKeyRepository
) {

    @Transactional
    fun addKey(userId: UUID, req: AddSshKeyRequest): SshKeyResponse {
        val fingerprint = SshFingerprint.sha256(req.publicKey)

        if (sshKeyRepository.existsByUserIdAndFingerprint(userId, fingerprint)) {
            throw DuplicateSshKeyException("This SSH key already exists for the user")
        }

        val entity = SshKeyEntity(
            id = null,
            userId = userId,
            publicKey = normalize(req.publicKey),
            fingerprint = fingerprint,
            title = req.title,
            revoked = false
        )

        val saved = sshKeyRepository.save(entity)
        return saved.toResponse()
    }

    @Transactional(readOnly = true)
    fun listActiveKeys(userId: UUID,pageable: Pageable): Page<SshKeyResponse> =
        sshKeyRepository.findAllByUserIdAndRevokedFalse(userId,pageable).map { it.toResponse() }

    @Transactional
    fun revoke(keyId: UUID, userId: UUID) {
        val key = sshKeyRepository.findById(keyId).orElseThrow { NoSuchElementException("Key not found") }
        if (key.userId != userId) throw IllegalStateException("Not allowed")
        if (key.revoked) return
        key.revoked = true
        sshKeyRepository.save(key)
    }

    @Transactional(readOnly = true)
    fun resolveUserByFingerprint(fingerprint: String): UUID? {
        return sshKeyRepository.findByFingerprintAndRevokedFalse(fingerprint)?.userId
    }

    private fun normalize(publicKey: String): String = publicKey.trim()

    private fun SshKeyEntity.toResponse() = SshKeyResponse(
        id = requireNotNull(id),
        title = title,
        fingerprint = fingerprint,
        revoked = revoked
    )
}