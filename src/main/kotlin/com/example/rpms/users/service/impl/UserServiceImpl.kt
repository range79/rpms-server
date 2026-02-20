package com.example.rpms.users.service.impl

import com.example.rpms.users.domain.entity.User
import com.example.rpms.users.domain.repository.UserRepository
import com.example.rpms.users.exception.UserNotFoundException
import com.example.rpms.users.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
): UserService {
    override fun findByUsername(username: String): User {
        return userRepository.findByUsername(username)?:throw UserNotFoundException("")
    }

    override fun findByEmail(email: String): User {
        TODO("Not yet implemented")
    }

}