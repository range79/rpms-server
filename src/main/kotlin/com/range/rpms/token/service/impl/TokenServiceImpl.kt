package com.range.rpms.token.service.impl

import com.range.rpms.token.domain.entity.Token
import com.range.rpms.token.domain.repository.TokenRepository
import com.range.rpms.token.properties.TokenProperties
import com.range.rpms.token.service.TokenService
import org.springframework.stereotype.Service

@Service
class TokenServiceImpl(
    private val repository: TokenRepository,
    private val tokenProperties: TokenProperties,
) : TokenService {
    override fun createToken(email: String): Token {
        val token = Token.createToken(email, tokenProperties.time)
        return repository.save(token)

    }

    override fun validateToken(token: String): Boolean {
            repository.findById(token).orElse(null) ?: return false
            repository.deleteById(token)
            return true
        }

    }
