package com.mogakso.common.config.interceptors;

import com.mogakso.common.errors.exceptions.auth.UnAuthorizeException;
import com.mogakso.domains.auth.domain.repository.UserRepository;
import com.mogakso.domains.auth.infra.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    private String HEADER_TOKEN_KEY = "accessToken";
    private String REFRESH_TOKEN_KEY = "refreshToken";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        String givenToken = request.getHeader(HEADER_TOKEN_KEY);
        boolean isAccessToken = true;

        if (givenToken == null) {
            givenToken = request.getHeader(REFRESH_TOKEN_KEY);
            isAccessToken = false;
        }

        if (givenToken == null) {
            throw new UnAuthorizeException();
        }

        verifyToken(givenToken, "a", isAccessToken);

        return true;
    }

    private void verifyToken(String givenToken, String userToken, boolean isAccessToken) {
        if (!givenToken.equals(userToken)) {
            throw new IllegalArgumentException("사용자의 토큰과 일치하지 않습니다.");
        }

        jwtUtil.verifyToken(givenToken, isAccessToken);
    }
}
