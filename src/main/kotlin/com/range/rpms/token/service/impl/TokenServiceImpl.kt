package com.range.rpms.token.service.impl

import com.range.rpms.token.domain.repository.TokenRepository
import com.range.rpms.token.service.TokenService
import org.springframework.stereotype.Service

@Service
class TokenServiceImpl(
    private val repository: TokenRepository,
) : TokenService {
    override fun createToken(email: String) {
        TODO()
    }

    override fun validateToken(token: String): Boolean {
        TODO("Not yet implemented")
    }
}