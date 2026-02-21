package com.range.rpms.token.service

import com.range.rpms.token.domain.entity.Token

interface TokenService {
    fun createToken(email:String): Token
    fun validateToken(token:String):Boolean
}