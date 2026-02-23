package com.range.rpms.users.controller

import com.range.rpms.users.api.AuthApi
import com.range.rpms.users.dto.ForgotPasswordRequest
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController: AuthApi {
    override fun login(loginRequest: LoginRequest) {
        TODO("Not yet implemented")
    }

    override fun register(registerRequest: RegisterRequest) {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest) {
        TODO("Not yet implemented")
    }


}