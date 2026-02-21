package com.range.rpms.users.service

import com.range.rpms.users.domain.entity.User

interface UserService {
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
    fun findByEmailORUsername(emailORUsername: String): User?
}