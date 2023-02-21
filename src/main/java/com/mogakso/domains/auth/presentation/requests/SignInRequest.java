package com.mogakso.domains.auth.presentation.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class SignInRequest {
    @Schema(description = "계정", example = "myAccount", required = true)
    @NotBlank
    private String account;
    @Schema(description = "비밀번호", example = "myPassword", required = true)
    @NotBlank
    private String password;

    public SignInRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
