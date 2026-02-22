package com.range.rpms.users.service.impl

import com.range.rpms.registerToken.service.ResetTokenService
import com.range.rpms.users.service.PasswordService
import org.springframework.stereotype.Service

@Service
class PasswordServiceImpl(
    private val resetTokenService: ResetTokenService
) : PasswordService {
    override fun sendPasswordResetEmail(email: String) {
        resetTokenService.createToken(email)
    }

    override fun verifyPasswordResetEmail(resetPassword: String) {
        TODO("Not yet implemented")
    }
}