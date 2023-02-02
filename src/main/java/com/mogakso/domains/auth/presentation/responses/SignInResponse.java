package com.mogakso.domains.auth.presentation.responses;

import com.mogakso.domains.auth.domain.entity.TokenEntity;
import org.springframework.lang.Nullable;

public class SignInResponse {
    private final String accessToken;
    private final String refreshToken;

    public SignInResponse(TokenEntity tokenEntity) {
        this.accessToken = tokenEntity.accessToken();
        this.refreshToken = tokenEntity.refreshToken();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}