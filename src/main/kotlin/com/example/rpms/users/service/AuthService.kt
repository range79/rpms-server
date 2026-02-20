package com.example.rpms.users.service

import com.example.rpms.users.dto.AuthResponse
import com.example.rpms.users.dto.LoginRequest
import com.example.rpms.users.dto.RegisterRequest

interface AuthService {
    fun login(loginRequest: LoginRequest): AuthResponse
    fun register(registerRequest: RegisterRequest)
    fun resetPassword(token: String)
    fun forgotPassword(email: String)
    fun twoFactoryAuthRequest(email:String)
    fun acceptTwoFactoryAuthRequest(token: String):AuthResponse
    fun logout()
}