package com.range.rpms.tokens.registerToken.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("reset-token")
data class ResetTokenProperties (
    val time: Long,
    val emailLimit: Long
)