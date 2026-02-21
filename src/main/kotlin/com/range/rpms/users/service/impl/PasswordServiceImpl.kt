package com.range.rpms.users.service.impl

import com.range.rpms.users.service.PasswordService
import org.springframework.stereotype.Service

@Service
class PasswordServiceImpl : PasswordService {
    override fun sendPasswordResetEmail(email: String) {

    }

    override fun verifyPasswordResetEmail(resetPassword: String) {
        TODO("Not yet implemented")
    }
}