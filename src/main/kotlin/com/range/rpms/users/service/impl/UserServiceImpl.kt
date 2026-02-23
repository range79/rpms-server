package com.range.rpms.users.service.impl

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.domain.repository.UserRepository
import com.range.rpms.users.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)

    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)

    }

    override fun findByEmailORUsername(emailORUsername: String): User? {
        return findByEmail(emailORUsername)
            ?: findByUsername(emailORUsername)

    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }


}