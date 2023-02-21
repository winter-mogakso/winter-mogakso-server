package com.mogakso.domains.auth.presentation.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @Schema(description = "계정", example = "myAccount", required = true)
        @NotBlank
        String account,
        @Schema(description = "비밀번호", example = "myPassword", required = true)
        @NotBlank
        String password,
        @Schema(description = "닉네임", example = "myNickname", required = true)
        @NotBlank
        String nickname) {
}
