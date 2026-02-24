package com.range.rpms.users.service

import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest
import com.range.rpms.users.dto.ResetPasswordRequest

interface AuthService {
    fun login(loginRequest: LoginRequest): AuthResponse
    fun register(registerRequest: RegisterRequest): AuthResponse
    fun resetPassword(resetPasswordRequest: ResetPasswordRequest)
    fun forgotPassword(email: String)
    fun acceptTwoFactoryAuthRequest(pendingId:String,token: String): AuthResponse
    fun logout()
}