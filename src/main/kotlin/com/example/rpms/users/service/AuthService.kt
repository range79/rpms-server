package com.example.rpms.users.service

import com.example.rpms.users.dto.AuthResponse
import com.example.rpms.users.dto.LoginRequest

interface AuthService {
    fun login(loginRequest: LoginRequest): AuthResponse
    fun register(registerRequest:RegisterRequest)
}