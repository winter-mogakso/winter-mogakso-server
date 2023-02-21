package com.mogakso.domains.auth.presentation.controllers;

import com.mogakso.common.swaggers.ApiDocumentResponse;
import com.mogakso.domains.auth.presentation.requests.CheckAccountRequest;
import com.mogakso.domains.auth.presentation.requests.CheckNicknameRequest;
import com.mogakso.domains.auth.presentation.requests.SignInRequest;
import com.mogakso.domains.auth.presentation.requests.SignUpRequest;
import com.mogakso.domains.auth.presentation.responses.SignInResponse;
import com.mogakso.domains.auth.presentation.responses.SignUpResponse;
import com.mogakso.domains.auth.presentation.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller", description = "유저 관련 컨트롤러")
@RestController
@RequestMapping(value = {"/user", "/spring-api/user"})
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiDocumentResponse
    @Operation(summary = "userSignIn", description = "유저 로그인")
    @PostMapping(value = "signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignInResponse> signInForForm(@RequestBody SignInRequest signInRequest) {
        return signIn(signInRequest);
    }

    @PostMapping(value = "signIn", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SignInResponse> signInForJson(SignInRequest signInRequest) {
        return signIn(signInRequest);
    }

    @ApiDocumentResponse
    @Operation(summary = "userSignUp", description = "유저 회원가입")
    @PostMapping(value = "signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignUpResponse> signUpForJson(@RequestBody SignUpRequest signUpRequest) {
        return signUp(signUpRequest);
    }

    @PostMapping(value = "signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SignUpResponse> signUpForForm(SignUpRequest signUpRequest) {
        return signUp(signUpRequest);
    }

    @ApiDocumentResponse
    @Operation(summary = "userCheckAccount", description = "계정 중복 확인")
    @GetMapping("checkAccount/{account}")
    public ResponseEntity checkAccount(@PathVariable String account) {
        try {
            if (account == null || account.length() < 3) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (userService.checkAccount(account)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiDocumentResponse
    @Operation(summary = "userCheckNickname", description = "닉네임 중복 확인")
    @GetMapping("checkNickname/{nickname}")
    public ResponseEntity checkNickname(@PathVariable String nickname) {
        try {
            if (nickname == null || nickname.length() < 3) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (userService.checkNickname(nickname)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private ResponseEntity<SignInResponse> signIn(SignInRequest signInRequest) {
        try {
            SignInResponse signInResponse = userService.signIn(signInRequest);

            if (signInResponse == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            return ResponseEntity.status(HttpStatus.OK).body(signInResponse);
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private ResponseEntity<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        try {
            SignUpResponse signUpResponse = userService.signUp((signUpRequest));

            if (signUpResponse == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
