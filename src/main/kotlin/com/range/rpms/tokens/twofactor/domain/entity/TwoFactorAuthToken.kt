package com.range.rpms.tokens.twofactor.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.time.Instant

@RedisHash("two_factor_auth")
data class TwoFactorAuthToken(

    @Id
    val id: String,

    val userId: String,

    val codeHash: String,

    val createdAt: Instant = Instant.now(),

    val attempts: Int = 0,

    val maxAttempts: Int = 5,

    val used: Boolean = false,

    @TimeToLive
    val ttl: Long
)