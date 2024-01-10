package com.routinise.service;

import com.routinise.config.JwtTokenProvider;
import com.routinise.domain.User;
import com.routinise.repository.UserRepository;
import com.routinise.request.Login;
import com.routinise.request.LoginResponse;
import com.routinise.request.UserCreate;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public void signup(UserCreate userCreate) {
        if (userRepository.countByPhone(userCreate.getPhone()) > 0) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        userRepository.save(userCreate.makeUser(passwordEncoder));
    }

    @Override
    public LoginResponse login(Login login) {
        User user = userRepository.findByPhone(login.getPhone())
                .filter(u -> passwordEncoder.matches(login.getPassword(), u.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        String token = jwtTokenProvider.createToken(user.getUuid());

        return user.makeLoginResponse(token);
    }
}
