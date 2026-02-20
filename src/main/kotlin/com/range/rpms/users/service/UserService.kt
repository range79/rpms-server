package com.range.rpms.users.service

import com.range.rpms.users.domain.entity.User

interface UserService {
    fun findByUsername(username: String): com.range.rpms.users.domain.entity.User
    fun findByEmail(email: String): com.range.rpms.users.domain.entity.User
}