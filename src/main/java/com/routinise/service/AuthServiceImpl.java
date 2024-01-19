package com.routinise.service;

import com.routinise.exception.Duplicate;
import com.routinise.security.JwtTokenProvider;
import com.routinise.domain.User;
import com.routinise.repository.UserRepository;
import com.routinise.request.Login;
import com.routinise.response.LoginResponse;
import com.routinise.request.Signup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void signup(Signup userCreate) {
        if (userRepository.countByPhone(userCreate.getPhone()) > 0) {
            throw new Duplicate("이미 가입된 전화번호입니다.");
        }

        if (userRepository.countByNickname(userCreate.getNickname()) > 0) {
            throw new Duplicate("이미 가입된 닉네임입니다.");
        }

        userRepository.save(userCreate.makeUser(passwordEncoder));
    }

    @Override
    public LoginResponse login(Login login) {
        User user = userRepository.findByPhone(login.getPhone())
                .filter(u -> passwordEncoder.matches(login.getPassword(), u.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        String token = jwtTokenProvider.createToken(String.format("%s:%s", user.getUuid(), user.getRole()));

        return user.makeLoginResponse(token);
    }


}
