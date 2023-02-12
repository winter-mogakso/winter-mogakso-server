package com.mogakso.domains.auth.presentation.requests;

public class CheckAccountRequest {
    private final String account;


    public CheckAccountRequest(String account) {
        this.account = account;

    }

    public String getAccount() {
        return account;
    }
}
