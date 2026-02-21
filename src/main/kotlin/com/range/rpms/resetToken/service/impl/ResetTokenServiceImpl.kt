package com.range.rpms.resetToken.service.impl

import com.range.rpms.registerToken.domain.entity.ResetToken
import com.range.rpms.registerToken.domain.repository.ResetTokenRepository
import com.range.rpms.registerToken.properties.ResetTokenProperties
import com.range.rpms.registerToken.service.ResetTokenService
import org.springframework.stereotype.Service

@Service
class ResetTokenServiceImpl(
    private val repository: ResetTokenRepository,
    private val properties: ResetTokenProperties,
) : ResetTokenService {
    override fun createToken(email: String): ResetToken {

        if (repository.countByEmail(email)>=properties.emailLimit) {
            throw
        }


        val registerToken = ResetToken.createToken(email, properties.time)
        return repository.save(registerToken)

    }

    override fun validateToken(token: String): Boolean {
            repository.findById(token).orElse(null) ?: return false
            repository.deleteById(token)
            return true
        }

}