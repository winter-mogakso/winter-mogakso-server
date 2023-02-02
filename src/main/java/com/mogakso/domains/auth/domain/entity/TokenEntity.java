package com.mogakso.domains.auth.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.annotation.AliasFor;

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
