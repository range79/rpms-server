package com.range.rpms.users.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties("password-reset")
data class PasswordResetProperties (
    val frontendURL: String,
)