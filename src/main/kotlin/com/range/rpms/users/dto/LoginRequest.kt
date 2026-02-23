package com.range.rpms.users.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class LoginRequest(

    @field:NotBlank(message = "Username or email cannot be blank")
    val usernameOrEmail: String,

    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(
        min = 8,
        max = 64,
        message = "Password must be between 8 and 64 characters"
    )
    var password: String,
)