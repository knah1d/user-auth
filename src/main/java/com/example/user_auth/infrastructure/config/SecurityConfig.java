package com.example.user_auth.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/**", "/h2-console/**", "/", "/index.html", "/static/**").permitAll()
                        .anyRequest().permitAll()) // Allow all requests for now since we're using API-based auth
                .formLogin(form -> form.disable()) // Disable form login since we're using API
                .httpBasic(basic -> basic.disable()) // Disable basic auth
                .logout(logout -> logout.permitAll())
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())); // For H2 console

        return http.build();
    }
}
