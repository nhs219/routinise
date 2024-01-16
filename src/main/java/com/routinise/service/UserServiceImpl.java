package com.routinise.service;

import com.routinise.domain.User;
import com.routinise.exception.NotFound;
import com.routinise.repository.UserRepository;
import com.routinise.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponse getUser(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(
                () -> new NotFound("사용자를 찾을 수 없습니다.")
        );

        return user.makeUserResponse();
    }
}
