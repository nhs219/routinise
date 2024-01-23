package com.routinise.service;

import com.routinise.domain.Role;
import com.routinise.domain.User;
import com.routinise.exception.NotFound;
import com.routinise.repository.UserRepository;
import com.routinise.request.UserUpdate;
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

        UserResponse userResponse = userService.getUser(user.getUserId());

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
    void updateUser() {
        User user = User.builder()
                .nickname("test")
                .phone("01012341234")
                .password("test1234")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String userId = user.getUserId();

        UserUpdate userForUpdate = UserUpdate.builder()
                .nickname("test2")
                .phone("01012345432")
                .password("test1234")
                .build();

        userService.updateUser(userForUpdate, userId);

        User updatedUser = userRepository.findByUserId(userId).get();

        assertThat(updatedUser.getNickname()).isEqualTo("test2");
        assertThat(updatedUser.getPhone()).isEqualTo("01012345432");
    }
}