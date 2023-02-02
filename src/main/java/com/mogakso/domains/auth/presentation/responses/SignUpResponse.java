package com.mogakso.domains.auth.presentation.responses;

import com.mogakso.domains.auth.domain.entity.TokenEntity;

public class SignUpResponse {
    private final String accessToken;
    private final String refreshToken;

    public SignUpResponse(TokenEntity tokenEntity) {
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