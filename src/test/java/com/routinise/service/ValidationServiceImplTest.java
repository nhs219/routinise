package com.routinise.service;

import com.routinise.domain.Role;
import com.routinise.domain.User;
import com.routinise.exception.Duplicate;
import com.routinise.repository.UserRepository;
import com.routinise.request.NickNameCheck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationServiceImplTest {

    @Autowired
    ValidationServiceImpl validationService;

    @Autowired
    UserRepository userRepository;

    @Test
    void checkNickname() {
        User user = User.builder()
                .nickname("test")
                .phone("01012341234")
                .password("test1234")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        NickNameCheck nickNameCheck = NickNameCheck.builder()
                .nickName("test")
                .build();

        Assertions.assertThatThrownBy(() -> validationService.checkDuplicateNickName(nickNameCheck))
                .isInstanceOf(Duplicate.class);
    }

}