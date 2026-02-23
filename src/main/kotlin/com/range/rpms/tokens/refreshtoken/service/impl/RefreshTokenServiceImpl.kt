package com.range.rpms.tokens.refreshtoken.service.impl

import com.range.rpms.common.dto.DeviceContextHolder
import com.range.rpms.tokens.refreshtoken.domain.entity.RefreshToken
import com.range.rpms.tokens.refreshtoken.domain.repository.RefreshTokenRepository
import com.range.rpms.tokens.refreshtoken.exception.InvalidRefreshTokenException
import com.range.rpms.tokens.refreshtoken.properties.RefreshTokenProperties
import com.range.rpms.tokens.refreshtoken.service.RefreshTokenService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.SecureRandom
import java.util.Base64
import java.util.UUID

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenProperties: RefreshTokenProperties,
) : RefreshTokenService {

    private val random = SecureRandom()

    private fun ttlSeconds(): Long =
        refreshTokenProperties.refreshTokenDays * 24L * 60L * 60L

    override fun issue(username: String): String {
        val device = DeviceContextHolder.get()
            ?: throw IllegalStateException("DeviceContext not found")

        val token = generateToken()
        val familyId = UUID.randomUUID().toString()

        refreshTokenRepository.save(
            RefreshToken(
                token = token,
                userId = username,
                familyId = familyId,
                deviceId = device.deviceId,
                deviceName = device.deviceName,
                revoked = false,
                ttl = ttlSeconds()
            )
        )
        return token
    }

    @Transactional
    override fun rotate(oldRefreshToken: String): String {
        val old = refreshTokenRepository.findById(oldRefreshToken)
            .orElseThrow { InvalidRefreshTokenException() }

        if (old.revoked) {
            refreshTokenRepository.deleteAllByUserIdAndFamilyId(old.userId, old.familyId)
            throw InvalidRefreshTokenException("Refresh token revoked")
        }

        refreshTokenRepository.save(old.copy(revoked = true))

        val newToken = generateToken()
        refreshTokenRepository.save(
            RefreshToken(
                token = newToken,
                userId = old.userId,
                familyId = old.familyId,
                deviceId = old.deviceId,
                deviceName = old.deviceName,
                revoked = false,
                ttl = ttlSeconds()
            )
        )

        return newToken
    }

    override fun revoke(token: String) {
        val found = refreshTokenRepository.findById(token).orElse(null) ?: return
        if (found.revoked) return
        refreshTokenRepository.save(found.copy(revoked = true))
    }

    override fun revokeAll(username: String) {
        refreshTokenRepository.deleteAllByUserId(username)
    }

    private fun generateToken(): String {
        val bytes = ByteArray(64)
        random.nextBytes(bytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
    }
}