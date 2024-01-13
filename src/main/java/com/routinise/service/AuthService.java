package com.routinise.service;

import com.routinise.request.Login;
import com.routinise.response.LoginResponse;
import com.routinise.request.Signup;

public interface AuthService {
    void signup(Signup userCreate);

    LoginResponse login(Login login);
}
