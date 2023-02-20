package com.mogakso.common.config;

import com.mogakso.common.config.interceptors.JwtAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final String PROD_AND_DEV_API_PREFIX = "/spring-api";
    private String[] INTERCEPTOR_WHITE_LIST = {
            "/user/**",
    };

    private final Environment environment;

    @Autowired
    public WebConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> activeProfiles = Arrays.stream(environment.getActiveProfiles()).toList();
        if (activeProfiles.contains("prod") || activeProfiles.contains("dev")) {
            for (int index = 0; index < INTERCEPTOR_WHITE_LIST.length; index++) {
                INTERCEPTOR_WHITE_LIST[index] = PROD_AND_DEV_API_PREFIX + INTERCEPTOR_WHITE_LIST[index];
            }
        }
        registry.addInterceptor(new JwtAuthInterceptor())
                .excludePathPatterns(INTERCEPTOR_WHITE_LIST);
    }
}
