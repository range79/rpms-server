package com.range.rpms.token.service

interface TokenService {
    fun createToken(email:String)
    fun validateToken(token:String):Boolean
}