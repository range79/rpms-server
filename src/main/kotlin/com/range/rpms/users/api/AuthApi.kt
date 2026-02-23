package com.range.rpms.users.api

import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.ForgotPasswordRequest
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping("/v1/api/auth")
interface AuthApi {
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): AuthResponse

    @PostMapping("/register")
    fun register(@RequestBody @Valid registerRequest: RegisterRequest): AuthResponse

    @PostMapping("/forgot-password")
    fun forgotPassword(@RequestBody @Valid forgotPasswordRequest: ForgotPasswordRequest)


}