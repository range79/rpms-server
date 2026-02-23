package com.range.rpms.users.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ResetPasswordRequest(

    @field:NotBlank(message = "Token cannot be blank")
    val token: String,

    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(
        min = 8,
        max = 64,
        message = "Password must be between 8 and 64 characters"
    )
    val newPassword: String
)