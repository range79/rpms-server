package com.range.rpms.tokens.jwt.service

import com.range.rpms.users.domain.entity.User
import org.springframework.stereotype.Service


interface JwtService {
    fun generateToken(user: User): String
}