package com.range.rpms.token.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.security.SecureRandom
import java.util.Base64

data class Token(
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
                        ttl: Long): Token {

            val bytes = ByteArray(32)
            random.nextBytes(bytes)

            val token = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes)

            return Token(
                token = token,
                email = email,
                ttl = 300
            )
        }
    }
}