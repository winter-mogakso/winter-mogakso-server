package com.mogakso.domains.auth.presentation.services;

import com.mogakso.domains.auth.domain.entity.TokenEntity;
import com.mogakso.domains.auth.domain.entity.UserEntity;
import com.mogakso.domains.auth.domain.repository.UserRepository;
import com.mogakso.domains.auth.infra.JwtUtil;
import com.mogakso.domains.auth.presentation.requests.SignInRequest;
import com.mogakso.domains.auth.presentation.requests.SignUpRequest;
import com.mogakso.domains.auth.presentation.responses.SignInResponse;
import com.mogakso.domains.auth.presentation.responses.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        verifyDuplicatedUser(signUpRequest.getAccount());

        TokenEntity tokenEntity = new TokenEntity(
                "a",
                "r");
        UserEntity userEntity = new UserEntity(
                "a",
                "p",
                "n",
                tokenEntity);

        return new SignUpResponse(tokenEntity);
        // userRepository.save();
    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        // UserEntity userEntity = userRepository.find();
        UserEntity userEntity = new UserEntity(
                "a",
                "p",
                "n",
                new TokenEntity(
                        "a",
                        "r"));

        if (!userEntity.password().equals(signInRequest.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        // TODO: Error code는 nullable로 해서 excepiton보다는 다른 걸로 처리
        return new SignInResponse(userEntity.token());
    }

    private void verifyDuplicatedUser(String account) {
        // if(userRepository.find() != null) throw new IllegalArgumentException("중복된 유저입니다.");
    }
}
