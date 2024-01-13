package com.routinise.response;

import com.routinise.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    private String nickName;

    private Role role;
    private String token;

    @Builder
    public LoginResponse(String nickName, Role role, String token) {
        this.nickName = nickName;
        this.role = role;
        this.token = token;
    }
}
