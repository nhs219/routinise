package com.routinise.service;

import com.routinise.response.UserResponse;

public interface UserService {
    UserResponse getUser(String uuid);
}
