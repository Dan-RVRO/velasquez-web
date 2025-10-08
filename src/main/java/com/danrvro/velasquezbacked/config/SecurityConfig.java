package com.danrvro.velasquezbacked.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for stateless/simple APIs and to avoid 403 on POST/PUT without tokens
                .csrf(AbstractHttpConfigurer::disable)

                // Authorize: allow everything (no authentication required)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

                // Basic sensible defaults; no login forms since we permit all
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)

                // Allow frames for H2 console if ever used
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}

