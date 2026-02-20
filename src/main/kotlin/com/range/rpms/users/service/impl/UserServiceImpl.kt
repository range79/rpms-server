package com.range.rpms.users.service.impl

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.domain.repository.UserRepository
import com.range.rpms.users.exception.UserNotFoundException
import com.range.rpms.users.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
):UserService {
    override fun findByUsername(username: String): User {
        return userRepository.findByUsername(username)?:throw UserNotFoundException(
            "User not found"
        )
    }

    override fun findByEmail(email: String): User {
      return userRepository.findByEmail(email)?:throw UserNotFoundException(
          "User not found"
      )
    }

}