package com.routinise.service;

import com.routinise.request.Login;
import com.routinise.request.LoginResponse;
import com.routinise.request.UserCreate;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void signup(UserCreate userCreate);

    LoginResponse login(Login login);
}
