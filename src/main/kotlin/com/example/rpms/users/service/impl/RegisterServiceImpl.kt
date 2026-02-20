package com.example.rpms.users.service.impl

import com.example.rpms.users.domain.entity.User
import com.example.rpms.users.dto.RegisterRequest
import com.example.rpms.users.service.RegisterService
import org.springframework.stereotype.Service

@Service
class RegisterServiceImpl: RegisterService {
    override fun register(registerRequest: RegisterRequest): User {
        val user = User(
            username = registerRequest.username,
            password = registerRequest.password
        )
    }
}