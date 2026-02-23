package com.range.rpms.tokens.refreshtoken.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "refresh-token")
data class RefreshTokenProperties(
    val refreshTokenDays: Long
)

