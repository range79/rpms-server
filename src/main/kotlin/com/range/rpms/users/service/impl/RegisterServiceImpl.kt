package com.range.rpms.users.service.impl

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.RegisterRequest
import com.range.rpms.users.service.RegisterService
import org.springframework.stereotype.Service

@Service
class RegisterServiceImpl: com.range.rpms.users.service.RegisterService {
    override fun register(registerRequest: com.range.rpms.users.dto.RegisterRequest): com.range.rpms.users.domain.entity.User {
        val user = _root_ide_package_.com.range.rpms.users.domain.entity.User(
            username = registerRequest.username,
            password = registerRequest.password
        )
    }
}