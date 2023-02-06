package com.mogakso.datas.auth.dtos;

import com.mogakso.domains.auth.domain.entity.TokenEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user")
public class UserDto {

    private String account;

    private String password;
    private String nickname;
    private Date accessTokenExpiredAt;
    private Date refreshTokenExpiredAt;
}
