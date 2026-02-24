package com.range.rpms.tokens.twofactor.dto

data class TwoFactorPending(
    val id: String,
    val expiresIn: Long
)