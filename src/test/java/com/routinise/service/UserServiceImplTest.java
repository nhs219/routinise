package com.routinise.service;

import com.routinise.domain.Role;
import com.routinise.domain.User;
import com.routinise.exception.NotFound;
import com.routinise.repository.UserRepository;
import com.routinise.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    void getUser() {
        User user = User.builder()
                .nickname("test")
                .phone("01012341234")
                .password("test1234")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        UserResponse userResponse = userService.getUser(user.getUuid());

        assertThat(userResponse.getNickName()).isEqualTo("test");
    }

    @Test
    void getUser_exception() {
        String uuid = "1234";

        assertThatThrownBy(
                () -> userService.getUser(uuid)
        ).isInstanceOf(NotFound.class);
    }

    @Test
    void makeUuid() {
        String uuid = UUID.randomUUID().toString();
    }

}