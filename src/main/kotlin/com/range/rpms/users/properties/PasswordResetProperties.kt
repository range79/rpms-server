package com.range.rpms.users.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("password-reset")
data class PasswordResetProperties (
    val frontendUrl: String,
)