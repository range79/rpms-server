package com.range.rpms.packages.security;

import com.range.rpms.common.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class PackagesSecurityConfig {

    private final JwtFilter jwtFilter;
    public PackagesSecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Order(4)
    @Bean
    public SecurityFilterChain packagesSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/v1/packages/**").csrf(csrf->csrf.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).authorizeHttpRequests(auth->
                        auth.requestMatchers("/packages/admin/**").hasRole("ADMIN")
                                .requestMatchers("/packages/user/**").hasAnyRole("USER", "ADMIN"));



        return http.build();
    }



}
