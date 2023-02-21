package com.mogakso.domains.auth.presentation.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CheckNicknameRequest(
        @Schema(description = "닉네임", example = "myNickname", required = true)
        @NotBlank
        String nickname) {
}
