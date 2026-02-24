package com.range.rpms.users.dto

data class AuthResponse(

    val accessToken: String? = null,
    val refreshToken: String? = null,


    val requiresTwoFactor: Boolean = false,
    val twoFactorPendingId: String? = null,
    val expiresIn: Long? = null

) {

    companion object {

        fun success(
            accessToken: String,
            refreshToken: String
        ): AuthResponse {
            return AuthResponse(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }

        fun twoFactorRequired(
            pendingId: String,
            expiresIn: Long
        ): AuthResponse {
            return AuthResponse(
                requiresTwoFactor = true,
                twoFactorPendingId = pendingId,
                expiresIn = expiresIn
            )
        }
    }
}