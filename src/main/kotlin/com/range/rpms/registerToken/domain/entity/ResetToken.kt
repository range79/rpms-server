package com.range.rpms.registerToken.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.security.SecureRandom
import java.util.Base64

data class ResetToken(
    @Id
    val token: String,

    @Indexed
    val email: String,

    @TimeToLive
    val ttl: Long
) {

    companion object {

        private val random = SecureRandom()

        fun createToken(email: String,
                        ttl: Long): ResetToken {

            val bytes = ByteArray(32)
            random.nextBytes(bytes)

            val token = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes)

            return ResetToken(
                token = token,
                email = email,
                ttl = ttl
            )
        }
    }
}