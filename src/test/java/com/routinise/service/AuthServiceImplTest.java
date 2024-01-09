package com.routinise.service;

import com.routinise.domain.Role;
import com.routinise.domain.User;
import com.routinise.repository.UserRepository;
import com.routinise.request.UserCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void signup() {
        UserCreate userCreate = UserCreate.builder()
                .nickname("nick-test")
                .phone("01012345678")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        authService.signup(userCreate);

        Assertions.assertThat(repository.count()).isEqualTo(1);
    }
}