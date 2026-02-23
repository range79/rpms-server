package com.range.rpms.users.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class ForgotPasswordRequest(

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email must be a valid email address")
    val email: String
)