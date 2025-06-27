package com.range.rpms.user.security;

import com.range.rpms.common.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Configuration
public class UserSecurity {
    private final JwtFilter jwtFilter;
    public UserSecurity(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    @Order(2)
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {

        http.securityMatcher("/v1/auth/")
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).csrf(
                        AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().permitAll());
        return http.build();
    }



}
