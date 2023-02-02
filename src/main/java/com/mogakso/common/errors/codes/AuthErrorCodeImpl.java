package com.mogakso.common.errors.codes;

import org.springframework.stereotype.Component;

@Component
public class AuthErrorCodeImpl implements ErrorCode {
    private final String ACCOUNT_NOT_EXISTS = "ERR-F-A01-A01";
    private final String PASSWORD_NOT_MATCHED = "ERR-F-A01-P01";

    public String getACCOUNT_NOT_EXISTS() {
        return ACCOUNT_NOT_EXISTS;
    }

    public String getPASSWORD_NOT_MATCHED() {
        return PASSWORD_NOT_MATCHED;
    }
}
