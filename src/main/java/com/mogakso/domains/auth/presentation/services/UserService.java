package com.mogakso.domains.auth.presentation.services;

import com.mogakso.domains.auth.domain.entity.TokenEntity;
import com.mogakso.domains.auth.domain.entity.UserEntity;
import com.mogakso.domains.auth.domain.repository.UserRepository;
import com.mogakso.domains.auth.infra.JwtUtil;
import com.mogakso.domains.auth.presentation.requests.SignInRequest;
import com.mogakso.domains.auth.presentation.requests.SignUpRequest;
import com.mogakso.domains.auth.presentation.responses.SignInResponse;
import com.mogakso.domains.auth.presentation.responses.SignUpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        verifyDuplicatedUser(signUpRequest.getAccount());

        if (userRepository.findByAccount(signUpRequest.getAccount()) != null) {
            log.info("[Service][update] user account %s exists", signUpRequest.getNickname());
            return null;
        }

        log.info("[Service][insert] new user creates");
        TokenEntity tokenEntity = new TokenEntity(jwtUtil.createToken(true), jwtUtil.createToken(false));
        UserEntity userEntity = new UserEntity(
                signUpRequest.getAccount(),
                signUpRequest.getPassword(),
                signUpRequest.getNickname(),
                tokenEntity.refreshToken());
        userRepository.save(userEntity);
        return new SignUpResponse(tokenEntity);
    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        UserEntity userEntity = userRepository.findByAccount(signInRequest.getAccount());
        if (userEntity == null) {
            log.info("[Service] account %s not exist", signInRequest.getAccount());
            return null;
        }

        if (!userEntity.password().equals(signInRequest.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        TokenEntity tokenEntity = new TokenEntity(jwtUtil.createToken(true), userEntity.refreshToken());
        return new SignInResponse(tokenEntity);
    }

    private void verifyDuplicatedUser(String account) {
        // if(userRepository.find() != null) throw new IllegalArgumentException("중복된 유저입니다.");
    }
}
