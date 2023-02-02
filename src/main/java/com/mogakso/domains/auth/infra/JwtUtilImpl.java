package com.mogakso.domains.auth.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtilImpl implements JwtUtil {
    private String ACCESS_SIGN_KEY = "TESTKEY";
    private String REFRESH_SIGN_KEY = "TESTKEY";
    private Date ACCESS_EXPIRED_TIME = new Date(System.currentTimeMillis() + 1000 * 10);
    private Date REFRESH_EXPIRED_TIME = new Date(System.currentTimeMillis() + 1000 * 10);
    private String ISSUER = "JJY";

    @Override
    public String createToken(boolean isAccessToken) {
        if (isAccessToken) {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withExpiresAt(ACCESS_EXPIRED_TIME)
                    .sign(Algorithm.HMAC256(ACCESS_SIGN_KEY));
        }

        return JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(REFRESH_EXPIRED_TIME)
                .sign(Algorithm.HMAC256(REFRESH_SIGN_KEY));
    }

    @Override
    public void verifyToken(String givenToken, boolean isAccessToken) {
        if (isAccessToken) {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(ACCESS_SIGN_KEY))
                    .withIssuer(ISSUER)
                    .build();

            verifier.verify(givenToken);
            return;
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(REFRESH_SIGN_KEY))
                .withIssuer(ISSUER)
                .build();

        verifier.verify(givenToken);
    }
}
