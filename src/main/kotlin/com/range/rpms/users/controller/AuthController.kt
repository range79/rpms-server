package com.range.rpms.users.controller

import com.range.rpms.users.api.AuthApi
import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.ForgotPasswordRequest
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest
import com.range.rpms.users.service.AuthService
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService,
): AuthApi {
    override fun login(loginRequest: LoginRequest): AuthResponse {
        return authService.login(loginRequest)
    }

    override fun register(registerRequest: RegisterRequest): AuthResponse {
        return authService.register(registerRequest)
    }

    override fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest) {
        return authService.forgotPassword(forgotPasswordRequest.email)
    }


}