package com.range.rpms.users.dto

data class LoginRequest (
    var usernameOREmail: String,
    var password: String,
)