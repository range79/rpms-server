package com.range.rpms.tokens.jwt.service.impl

import com.range.rpms.tokens.jwt.properties.JwtProperties
import com.range.rpms.tokens.jwt.service.JwtService
import com.range.rpms.users.domain.entity.User
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtServiceImpl(
    private val jwtEncoder: JwtEncoder,
    private val jwtProperties: JwtProperties
): JwtService {
    override fun generateToken(user: User): String {
val now = Instant.now();
        val claims= JwtClaimsSet.builder()
            .subject(user.username)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(jwtProperties.expiresAt*60))
            .claim("role", user.role)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}
