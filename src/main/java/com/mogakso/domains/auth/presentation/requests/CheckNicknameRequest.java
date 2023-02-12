package com.mogakso.domains.auth.presentation.requests;

public class CheckNicknameRequest {
    private final String nickname;


    public CheckNicknameRequest(String nickname) {
        this.nickname = nickname;

    }

    public String getNickname() {
        return nickname;
    }
}
