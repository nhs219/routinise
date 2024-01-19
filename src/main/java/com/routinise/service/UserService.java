package com.routinise.service;

import com.routinise.request.UserUpdate;
import com.routinise.response.UserResponse;

public interface UserService {
    UserResponse getUser(String uuid);

    void updateUser(UserUpdate user, String userId);
}
