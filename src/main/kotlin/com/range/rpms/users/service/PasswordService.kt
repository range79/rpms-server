package com.range.rpms.users.service

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.ResetPasswordRequest

interface PasswordService {
    fun sendPasswordResetEmail(email: String)
    fun verifyPasswordResetEmail(resetPassword: ResetPasswordRequest): User
}