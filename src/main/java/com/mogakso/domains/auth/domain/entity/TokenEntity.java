package com.mogakso.domains.auth.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenEntity(
        @JsonProperty("accessToken") String accessToken,

        @JsonProperty("refreshToken") String refreshToken
) {
    public TokenEntity {
        if (accessToken == null || refreshToken == null) {
            throw new IllegalArgumentException();
        }
    }
}
