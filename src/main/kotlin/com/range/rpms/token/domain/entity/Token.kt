package com.range.rpms.token.domain.entity

import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

data class Token (
    val token: String,
    @Indexed
    val email: String,
    @TimeToLive
    val time: Long,
)