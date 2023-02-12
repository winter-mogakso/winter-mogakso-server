package com.mogakso.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// WebSecurityConfigurerAdapter는 deprecated
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] API_WHITE_LIST = {
            "/api/user/signUp",
            "/api/user/signIn",
            "/api/user/checkAccount",
            "/api/user/checkNickname"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
            Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests().requestMatchers(API_WHITE_LIST).permitAll()
                .and().build();
    }
}
