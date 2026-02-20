package com.range.rpms.users.service.impl

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.domain.repository.UserRepository
import com.range.rpms.users.exception.UserNotFoundException
import com.range.rpms.users.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: com.range.rpms.users.domain.repository.UserRepository,
): com.range.rpms.users.service.UserService {
    override fun findByUsername(username: String): com.range.rpms.users.domain.entity.User {
        return userRepository.findByUsername(username)?:throw _root_ide_package_.com.range.rpms.users.exception.UserNotFoundException(
            "User not found"
        )
    }

    override fun findByEmail(email: String): com.range.rpms.users.domain.entity.User {
      return userRepository.findByEmail(email)?:throw _root_ide_package_.com.range.rpms.users.exception.UserNotFoundException(
          "User not found"
      )
    }

}