package com.routinise.service;

import com.routinise.domain.User;
import com.routinise.exception.NotFound;
import com.routinise.repository.UserRepository;
import com.routinise.request.UserUpdate;
import com.routinise.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new NotFound("사용자를 찾을 수 없습니다.")
        );

        return user.makeUserResponse();
    }

    @Override
    public void updateUser(UserUpdate userUpdate, String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new NotFound("사용자를 찾을 수 없습니다.")
        );

        userUpdate.userForUpdate(user, encoder);

        userRepository.save(user);
    }
}
