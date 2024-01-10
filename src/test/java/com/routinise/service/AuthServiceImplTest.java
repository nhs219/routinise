package com.routinise.service;

import com.routinise.domain.Role;
import com.routinise.domain.User;
import com.routinise.repository.UserRepository;
import com.routinise.request.Login;
import com.routinise.request.UserCreate;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import java.util.Base64;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void init() {
        repository.deleteAll();
    }

    @Test
    void signup() {
        UserCreate userCreate = UserCreate.builder()
                .nickname("nick-test")
                .phone("01012345678")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        authService.signup(userCreate);

        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void signup_duplicate_exception() {
        UserCreate userCreate = UserCreate.builder()
                .nickname("nick-test")
                .phone("01012345678")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        authService.signup(userCreate);

        assertThatThrownBy(() -> authService.signup(userCreate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void login() {
        UserCreate userCreate = UserCreate.builder()
                .nickname("nick-test")
                .phone("01012345678")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        authService.signup(userCreate);

        Login loginId = Login.builder()
                .phone("01012345678")
                .password("1234")
                .build();

        authService.login(loginId);
    }

    @Test
    void login_exception() {
        UserCreate userCreate = UserCreate.builder()
                .nickname("nick-test")
                .phone("01012345678")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        authService.signup(userCreate);

        Login login = Login.builder()
                .phone("01012345678")
                .password("12345")
                .build();

        assertThatThrownBy(() -> authService.login(login))
                .isInstanceOf(IllegalArgumentException.class);
    }
}