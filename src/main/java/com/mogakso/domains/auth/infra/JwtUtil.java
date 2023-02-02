package com.mogakso.domains.auth.infra;

import org.springframework.stereotype.Component;

@Component
public interface JwtUtil {
    String createToken(boolean isAccessToken);

    void verifyToken(String givenToken, boolean isAccessToken);
}
