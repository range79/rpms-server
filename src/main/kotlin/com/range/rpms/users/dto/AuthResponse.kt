package com.range.rpms.users.dto

data class AuthResponse (
    val refreshToken: String,
    val accessToken: String,
)
