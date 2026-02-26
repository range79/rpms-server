package com.range.rpms.repository.service.impl

import com.range.rpms.common.exception.BadRequestException
import com.range.rpms.common.exception.ForbiddenException
import com.range.rpms.repository.crypto.SshFingerprint
import com.range.rpms.repository.domain.entity.SshKeyEntity
import com.range.rpms.repository.domain.repository.SshKeyRepository
import com.range.rpms.repository.dto.AddSshKeyRequest
import com.range.rpms.repository.dto.SshKeyResponse
import com.range.rpms.repository.exception.DuplicateSshKeyException
import com.range.rpms.repository.exception.SshKeyNotFoundException
import com.range.rpms.repository.service.SshKeyService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class SshKeyServiceImpl(
    private val sshKeyRepository: SshKeyRepository
) : SshKeyService {

    @Transactional
    override fun addKey(userId: UUID, req: AddSshKeyRequest): SshKeyResponse {
        val normalizedKey = normalize(req.publicKey)
        val fingerprint = SshFingerprint.sha256(normalizedKey)

        if (sshKeyRepository.existsByUserIdAndFingerprint(userId, fingerprint)) {
            throw DuplicateSshKeyException()
        }

        val title = req.title.trim()
        if (title.isBlank()) {
            throw BadRequestException("Title must not be blank")
        }

        val entity = SshKeyEntity(
            id = null,
            userId = userId,
            publicKey = normalizedKey,
            fingerprint = fingerprint,
            title = title,
            revoked = false
        )

        return sshKeyRepository.save(entity).toResponse()
    }

    @Transactional(readOnly = true)
    override fun listActiveKeys(userId: UUID, pageable: Pageable): Page<SshKeyResponse> =
        sshKeyRepository
            .findAllByUserIdAndRevokedFalse(userId, pageable)
            .map { it.toResponse() }

    @Transactional
    override fun revoke(keyId: UUID, userId: UUID) {
        val key = sshKeyRepository.findById(keyId)
            .orElseThrow { SshKeyNotFoundException() }

        if (key.userId != userId) throw ForbiddenException()
        if (key.revoked) return

        key.revoked = true
        sshKeyRepository.save(key)
    }

    @Transactional(readOnly = true)
    override fun resolveUserByFingerprint(fingerprint: String): UUID? =
        sshKeyRepository
            .findByFingerprintAndRevokedFalse(fingerprint.trim())
            ?.userId

    private fun normalize(publicKey: String): String =
        publicKey.trim()

    private fun SshKeyEntity.toResponse() =
        SshKeyResponse(
            id = requireNotNull(id),
            title = title,
            fingerprint = fingerprint,
            revoked = revoked
        )
}