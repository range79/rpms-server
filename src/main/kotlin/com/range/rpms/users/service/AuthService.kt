package com.range.rpms.users.service

import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest

interface AuthService {
    fun login(loginRequest: LoginRequest): AuthResponse
    fun register(registerRequest: RegisterRequest)
    fun resetPassword(token: String)
    fun forgotPassword(email: String)
    fun twoFactoryAuthRequest(email:String)
    fun acceptTwoFactoryAuthRequest(token: String): AuthResponse
    fun logout()
}