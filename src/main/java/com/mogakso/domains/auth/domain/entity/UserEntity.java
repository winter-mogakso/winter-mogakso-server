package com.mogakso.domains.auth.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserEntity(
        @JsonProperty("account")
        String account,
        @JsonProperty("password")
        String password,
        @JsonProperty("nickname")
        String nickname,
        TokenEntity token
) {
}

