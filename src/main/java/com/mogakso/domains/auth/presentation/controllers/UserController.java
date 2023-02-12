package com.mogakso.domains.auth.presentation.controllers;

import com.mogakso.domains.auth.presentation.requests.CheckAccountRequest;
import com.mogakso.domains.auth.presentation.requests.CheckNicknameRequest;
import com.mogakso.domains.auth.presentation.requests.SignInRequest;
import com.mogakso.domains.auth.presentation.requests.SignUpRequest;
import com.mogakso.domains.auth.presentation.responses.SignInResponse;
import com.mogakso.domains.auth.presentation.responses.SignUpResponse;
import com.mogakso.domains.auth.presentation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignInResponse> signInForForm(@RequestBody SignInRequest signInRequest) {
        return signIn(signInRequest);
    }

    @PostMapping(value = "signIn", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SignInResponse> signInForJson(SignInRequest signInRequest) {
        return signIn(signInRequest);
    }

    @PostMapping(value = "signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignUpResponse> signUpForJson(@RequestBody SignUpRequest signUpRequest) {
        return signUp(signUpRequest);
    }

    @PostMapping(value = "signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SignUpResponse> signUpForForm(SignUpRequest signUpRequest) {
        return signUp(signUpRequest);
    }

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
