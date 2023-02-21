package com.mogakso.domains.auth.presentation.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CheckAccountRequest(
        @Schema(description = "계정", example = "myAccount", required = true)
        @NotBlank
        String account) {
}
