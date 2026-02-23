package com.range.rpms.users.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class RegisterRequest(

    @field:NotBlank(message = "Username cannot be blank")
    @field:Size(
        min = 3,
        max = 20,
        message = "Username must be between 3 and 20 characters"
    )
    @field:Pattern(
        regexp = "^[a-zA-Z0-9_]+$",
        message = "Username can contain only letters, numbers, and underscores"
    )
    val username: String,

    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(
        min = 8,
        max = 64,
        message = "Password must be between 8 and 64 characters"
    )
    val password: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email must be a valid email address")
    val email: String,
)