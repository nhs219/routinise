package com.routinise.response;

import com.routinise.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class UserResponse {

    private String nickName;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public UserResponse(String nickName, String email, Role role, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.nickName = nickName;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
