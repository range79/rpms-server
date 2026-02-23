package com.range.rpms.tokens.refreshtoken.service

import com.range.rpms.users.domain.entity.User


interface RefreshTokenService {


    fun issue(user: User): String


    fun rotate(oldRefreshToken: String): String


    fun revoke(token: String)

    fun revokeAll(username: String)

 //   fun listSessions(username: String): List<RefreshSession>
}