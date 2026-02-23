package com.range.rpms.users.api

import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.ForgotPasswordRequest
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping("/v1/api/auth")
interface AuthApi {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): AuthResponse

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): AuthResponse

    @PostMapping("/forgot-password")
    fun forgotPassword(@RequestBody forgotPasswordRequest: ForgotPasswordRequest)


}