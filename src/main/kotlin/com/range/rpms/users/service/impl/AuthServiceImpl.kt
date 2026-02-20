package com.range.rpms.users.service.impl

import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest
import com.range.rpms.users.service.AuthService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl (

): com.range.rpms.users.service.AuthService {
    override fun login(loginRequest: com.range.rpms.users.dto.LoginRequest): com.range.rpms.users.dto.AuthResponse {
        TODO("Not yet implemented")
    }

    override fun register(registerRequest: com.range.rpms.users.dto.RegisterRequest) {
        TODO("Not yet implemented")
    }

    override fun resetPassword(token: String) {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(email: String) {
        TODO("Not yet implemented")
    }

    override fun twoFactoryAuthRequest(email: String) {
        TODO("Not yet implemented")
    }

    override fun acceptTwoFactoryAuthRequest(token: String): com.range.rpms.users.dto.AuthResponse {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}