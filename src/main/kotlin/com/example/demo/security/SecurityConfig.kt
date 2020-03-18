package com.example.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .build()
    }

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val userDetails = User
                .withUsername("admin")
                .password(passwordEncoder().encode("qwerty"))
                .roles("ADMIN")
                .build()

        return MapReactiveUserDetailsService(userDetails)
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}
