package com.range.rpms.tokens.tokenfactory

import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.AuthResponse
import org.springframework.stereotype.Service


interface TokenFactory {

    fun generateTokens(user: User): AuthResponse
}