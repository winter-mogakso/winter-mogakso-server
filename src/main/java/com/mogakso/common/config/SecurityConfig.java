package com.mogakso.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// WebSecurityConfigurerAdapter는 deprecated
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties
public class SecurityConfig {
    private final String PROD_AND_DEV_API_PREFIX = "/spring-api";
    private final String[] API_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/user/signUp",
            "/user/signIn",
            "/user/checkAccount",
            "/user/checkNickname"
    };

    private final Environment environment;

    @Autowired
    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
            Exception {
        List<String> activeProfiles = Arrays.stream(environment.getActiveProfiles()).toList();
        if (activeProfiles.contains("prod") || activeProfiles.contains("dev")) {
            for (int index = 0; index < API_WHITE_LIST.length; index++) {
                API_WHITE_LIST[index] = PROD_AND_DEV_API_PREFIX + API_WHITE_LIST[index];
            }
        }
        return httpSecurity
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests().requestMatchers(API_WHITE_LIST).permitAll()
                .and().build();
    }
}
