package com.range.rpms.tokens.jwt.service.impl

import com.range.rpms.tokens.jwt.properties.JwtProperties
import com.range.rpms.tokens.jwt.service.JwtService
import com.range.rpms.users.domain.entity.User
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtServiceImpl(
    private val jwtEncoder: JwtEncoder,
    private val jwtProperties: JwtProperties
) : JwtService {
    override fun generateToken(user: User): String {
        val now = Instant.now()
        val headers = JwsHeader.with(MacAlgorithm.HS256).build()

        val claims = JwtClaimsSet.builder()
            .subject(user.id.toString())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(jwtProperties.expiresAt * 60))
            .claim("role", user.role.name)
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).tokenValue
    }
}
