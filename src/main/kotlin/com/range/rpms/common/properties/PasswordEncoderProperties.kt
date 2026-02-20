package com.range.rpms.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("password-encoder")
data class PasswordEncoderProperties (
    var saltLength: String,
    var hashLength: Int,
    var parallelism: Int,
    var memory: Int,
    var iterations: Int
)