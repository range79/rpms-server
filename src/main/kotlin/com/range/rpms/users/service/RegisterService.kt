package com.range.rpms.users.service

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.RegisterRequest

interface RegisterService {
    fun register(registerRequest: com.range.rpms.users.dto.RegisterRequest): com.range.rpms.users.domain.entity.User
}