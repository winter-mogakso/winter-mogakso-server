package com.mogakso.domains.auth.domain.repository;

import com.mogakso.domains.auth.domain.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByAccount(String account);
    UserEntity findByNickname(String nickname);
}
