package com.range.rpms.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("password-encoder")
data class PasswordEncoderProperties (
    var saltLength: Int,
    var hashLength: Int,
    var parallelism: Int,
    var memory: Int,
    var iterations: Int
)