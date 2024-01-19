package com.routinise.repository;

import com.routinise.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    int countByPhone(String phone);

    Optional<User> findByPhone(String phone);

    Optional<User> findByUserId(String userId);

    int countByNickname(String nickname);

    int countByNickName(String nickName);
}
