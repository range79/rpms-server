package com.range.rpms.tokens.refreshtoken.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash("refresh_token")
data class RefreshToken(
    @Id
    val token: String,
    @Indexed
    val userId: String,
    @Indexed
    val familyId: String,
    val deviceName: String? = null,
    val revoked: Boolean = false,
    @TimeToLive
    val ttl: Long
)