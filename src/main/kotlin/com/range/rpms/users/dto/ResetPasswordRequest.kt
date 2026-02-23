package com.range.rpms.users.dto

data class ResetPasswordRequest(
    val token: String,
    val newPassword: String
)