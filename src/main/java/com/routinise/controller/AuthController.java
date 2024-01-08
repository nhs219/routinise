package com.routinise.controller;

import com.routinise.domain.User;
import com.routinise.repository.UserRepository;
import com.routinise.request.Login;
import com.routinise.request.UserCreate;
import com.routinise.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid UserCreate userCreate) {
        authService.signup(userCreate);
    }

}
