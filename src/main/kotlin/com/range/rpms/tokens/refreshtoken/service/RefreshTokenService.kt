package com.range.rpms.tokens.refreshtoken.service



interface RefreshTokenService {


    fun issue(username: String): String


    fun rotate(oldRefreshToken: String): String


    fun revoke(token: String)

    fun revokeAll(username: String)

 //   fun listSessions(username: String): List<RefreshSession>
}