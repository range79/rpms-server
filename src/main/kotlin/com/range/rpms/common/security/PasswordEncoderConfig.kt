package com.range.rpms.common.security

import com.range.rpms.common.properties.PasswordEncoderProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordEncoderConfig (
    private val properties: PasswordEncoderProperties
){




    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return  Argon2PasswordEncoder(
            properties.saltLength,
            properties.hashLength,
            properties.parallelism,
            properties.memory,
            properties.iterations
        )
    }
}