package com.mogakso.domains.auth.presentation.requests;

public class SignInRequest {
    private String account;
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
