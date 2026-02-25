package com.range.rpms.common.config

import com.range.rpms.common.filters.BanCheckFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val banCheckFilter: BanCheckFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .csrf { it.disable() }

            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

            .authorizeHttpRequests {
                it.requestMatchers("/login/callback").permitAll()
                it.requestMatchers("/auth/**", "/public/**").permitAll()
                it.requestMatchers("/admin/**").hasRole("ADMIN")
                it.anyRequest().authenticated()
            }

            .oauth2ResourceServer {
                it.jwt {}
            }

            .addFilterAfter(
                banCheckFilter,
                BearerTokenAuthenticationFilter::class.java
            )

        return http.build()
    }
}