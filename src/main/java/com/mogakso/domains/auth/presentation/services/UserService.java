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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.findByAccount(signUpRequest.getAccount()) != null) {
            log.info("[Service][update] user account %s exists", signUpRequest.getNickname());
            return null;
        }

        log.info("[Service][insert] new user creates");
        TokenEntity tokenEntity = new TokenEntity(jwtUtil.createToken(true), jwtUtil.createToken(false));
        UserEntity userEntity = new UserEntity(
                signUpRequest.getAccount(),
                passwordEncoder.encode(signUpRequest.getPassword()),
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

        if (!passwordEncoder.matches(userEntity.password(), signInRequest.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        TokenEntity tokenEntity = new TokenEntity(jwtUtil.createToken(true), userEntity.refreshToken());
        return new SignInResponse(tokenEntity);
    }
}
