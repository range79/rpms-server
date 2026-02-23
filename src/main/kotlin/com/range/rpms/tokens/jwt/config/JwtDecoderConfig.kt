package com.range.rpms.tokens.jwt.config

import com.range.rpms.tokens.jwt.properties.JwtProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import java.nio.charset.StandardCharsets
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtDecoderConfig(
    private val jwtProperties: JwtProperties
) {
    @Bean
    fun jwtDecoder(): JwtDecoder {
        val key = SecretKeySpec(
            jwtProperties.secret.toByteArray(StandardCharsets.UTF_8),
            "HmacSHA256"
        )

        return NimbusJwtDecoder
            .withSecretKey(key)
            .macAlgorithm(MacAlgorithm.HS256)
            .build()
    }
}