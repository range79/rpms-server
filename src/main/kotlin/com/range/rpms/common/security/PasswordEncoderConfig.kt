package com.range.rpms.common.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordEncoderConfig {




    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return  Argon2PasswordEncoder()
    }
}