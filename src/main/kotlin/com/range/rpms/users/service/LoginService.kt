package com.range.rpms.users.service

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.LoginRequest

interface LoginService {
    fun login(loginRequest: LoginRequest): User
}