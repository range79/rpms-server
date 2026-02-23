package com.range.rpms.tokens.registerToken.service.impl

import com.range.rpms.tokens.registerToken.domain.entity.ResetToken
import com.range.rpms.tokens.registerToken.domain.repository.ResetTokenRepository
import com.range.rpms.tokens.registerToken.exception.ResetTokenLimitExceededException
import com.range.rpms.tokens.registerToken.properties.ResetTokenProperties
import com.range.rpms.tokens.registerToken.service.ResetTokenService
import org.springframework.stereotype.Service

@Service
class ResetTokenServiceImpl(
    private val repository: ResetTokenRepository,
    private val properties: ResetTokenProperties,
) : ResetTokenService {
    override fun createToken(email: String): ResetToken {

        if (repository.countByEmail(email)>=properties.emailLimit) {
            throw ResetTokenLimitExceededException()
        }


        val registerToken = ResetToken.createToken(email, properties.time)
        return repository.save(registerToken)

    }

    override fun validateToken(token: String): String? {
            val foundToken =repository.findById(token).orElse(null) ?: return null
            repository.delete(foundToken)
            return foundToken.email
        }

}