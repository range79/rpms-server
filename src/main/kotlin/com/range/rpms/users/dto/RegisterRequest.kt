package com.range.rpms.users.dto

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
)
