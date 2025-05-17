package com.range.rpms.common.security;

import com.range.rpms.common.security.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .disable()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/login",
                                "/swagger",
                                "v3/api-docs",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/register",
                                "auth/register",
                                "auth/login"
                        ).permitAll()

                        .requestMatchers("/info/all",
                                "packages/delete/all",
                                "packages/delete/"
                                //for now l set it admin but l will change it in new version

                        ).hasRole("ADMIN")



.requestMatchers("/").hasRole("USER")


                        .anyRequest().permitAll()
                );



        return http.build();
    }





    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
