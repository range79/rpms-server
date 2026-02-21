package com.range.rpms.users.service.impl

import com.range.rpms.users.domain.entity.AccountStatus
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.exception.AccountBannedException
import com.range.rpms.users.exception.AccountSuspendedException
import com.range.rpms.users.exception.AuthenticationException
import com.range.rpms.users.service.LoginService
import com.range.rpms.users.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) : LoginService {
    override fun login(loginRequest: LoginRequest): User {
        val user = userService.findByEmailORUsername(loginRequest.usernameOREmail) ?: throw AuthenticationException()
        accountChecker(user.accountStatus)
        if (!passwordEncoder.matches(loginRequest.password, user.password)){
            throw AuthenticationException()
        }
        return user

    }

    private fun accountChecker(status: AccountStatus) {
        when (status) {
            AccountStatus.ACTIVE-> Unit
            AccountStatus.BANNED -> throw AccountBannedException()
            AccountStatus.SUSPENDED -> throw AccountSuspendedException()
            else -> return
        }
    }

}