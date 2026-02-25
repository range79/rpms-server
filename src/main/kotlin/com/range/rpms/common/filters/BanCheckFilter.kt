package com.range.rpms.common.filters

import com.range.rpms.users.domain.entity.AccountStatus
import com.range.rpms.users.domain.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class BanCheckFilter(
    private val userRepository: UserRepository

) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val auth = SecurityContextHolder.getContext().authentication

        if (auth is JwtAuthenticationToken) {

            val keycloakId = auth.token.subject

            val user = userRepository.findByKeycloakId(keycloakId)

            if (user != null && user.accountStatus== AccountStatus.BANNED) {
                response.status = HttpStatus.FORBIDDEN.value()
                response.writer.write("User is banned")
                return
            }
        }

        filterChain.doFilter(request, response)
    }
}