package com.example.rpms.users.service

import com.example.rpms.users.domain.entity.User

interface UserService {
    fun findByUsername(username: String): User
    fun findByEmail(email: String): User
}