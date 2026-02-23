package com.range.rpms.tokens.tokenfactory.impl

import com.range.rpms.tokens.jwt.service.JwtService
import com.range.rpms.tokens.refreshtoken.service.RefreshTokenService
import com.range.rpms.tokens.tokenfactory.TokenFactory
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.AuthResponse
import org.springframework.stereotype.Service

@Service
class TokenFactoryImpl (
    private val jwtService: JwtService,
    private val refreshTokenService: RefreshTokenService
): TokenFactory {
    override fun generateTokens(user: User): AuthResponse {

        val accessToken=jwtService.generateToken(user)
        val refreshToken=refreshTokenService.issue(user)
        return AuthResponse(refreshToken, accessToken)


    }
}