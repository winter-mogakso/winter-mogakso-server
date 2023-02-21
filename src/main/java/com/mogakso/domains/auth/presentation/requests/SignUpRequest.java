package com.mogakso.domains.auth.presentation.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        String account,
        String password,
        String nickname) {
}
