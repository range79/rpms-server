package com.range.rpms.friend.security;

import com.range.rpms.common.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class FriendSecurity {

    private final JwtFilter jwtFilter;

    public FriendSecurity(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    @Order(3)
    public SecurityFilterChain friendChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/friends/**")
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().hasAnyRole("USER", "ADMIN") // rollerini buna göre ayarla
                );
        return http.build();
    }
}







