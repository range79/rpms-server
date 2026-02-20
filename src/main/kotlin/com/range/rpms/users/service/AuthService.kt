package com.range.rpms.users.service

import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest

interface AuthService {
    fun login(loginRequest: com.range.rpms.users.dto.LoginRequest): com.range.rpms.users.dto.AuthResponse
    fun register(registerRequest: com.range.rpms.users.dto.RegisterRequest)
    fun resetPassword(token: String)
    fun forgotPassword(email: String)
    fun twoFactoryAuthRequest(email:String)
    fun acceptTwoFactoryAuthRequest(token: String): com.range.rpms.users.dto.AuthResponse
    fun logout()
}