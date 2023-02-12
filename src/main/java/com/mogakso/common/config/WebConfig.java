package com.mogakso.common.config;

import com.mogakso.common.config.interceptors.JwtAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String[] INTERCEPTOR_WHITE_LIST = {
            "/api/user/**",
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtAuthInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns(INTERCEPTOR_WHITE_LIST);
    }
}
