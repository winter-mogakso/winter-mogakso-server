package com.mogakso.domains.auth.presentation.requests;

public class SignUpRequest {
    private final String account;
    private final String password;
    private final String nickname;

    public SignUpRequest(String account, String password, String nickname) {
        this.account = account;
        this.password = password;
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
