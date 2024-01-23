package com.routinise.repository;

import com.routinise.domain.User;
import org.apache.el.stream.Stream;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    int countByNickname(String nickname);

    int countByEmail(String email);

    Optional<User> findByEmail(String email);
}
