package com.range.rpms.users.service.impl

import com.range.rpms.common.exception.PasswordEncoderException
import com.range.rpms.users.domain.entity.Role
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.domain.repository.UserRepository
import com.range.rpms.users.dto.RegisterRequest
import com.range.rpms.users.service.RegisterService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterServiceImpl(
    private val userRepository : UserRepository,
    private val passwordEncoder: PasswordEncoder
    ): RegisterService {
    @Transactional
    override fun register(registerRequest: RegisterRequest): User {
        val user = User(
            id = null,
            email = registerRequest.email,
            username = registerRequest.username,
            password = passwordEncoder.encode(registerRequest.password)?:throw PasswordEncoderException("server have problem with ur account"),
            role = Role.USER
        )
        userRepository.save(user)
        return user
    }
}