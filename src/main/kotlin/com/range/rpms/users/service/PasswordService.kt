package com.range.rpms.users.service

interface PasswordService {
    fun sendPasswordResetEmail(email: String)
    fun verifyPasswordResetEmail(resetPassword: String)
}