package com.example.rpms.users.service

import com.example.rpms.users.domain.entity.User
import com.example.rpms.users.dto.RegisterRequest

interface RegisterService {
    fun register(registerRequest: RegisterRequest): User
}