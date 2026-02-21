package com.range.rpms.token.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("reset-token")
data class TokenProperties (
    val time: Long,
)