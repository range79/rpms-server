package com.range.rpms.tokens.jwt.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.OctetSequenceKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.range.rpms.tokens.jwt.properties.JwtProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import java.nio.charset.StandardCharsets
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtEncoderConfig(
    private val jwtProperties: JwtProperties
) {

    @Bean
    fun jwtEncoder(): JwtEncoder {

        val secret = jwtProperties.secret.toByteArray(StandardCharsets.UTF_8)

        val jwk = OctetSequenceKey.Builder(secret)
            .algorithm(com.nimbusds.jose.JWSAlgorithm.HS256)
            .keyID("jwt-key")
            .build()

        val jwkSet = JWKSet(jwk)

        return NimbusJwtEncoder(ImmutableJWKSet(jwkSet))
    }
}