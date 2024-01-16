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
    private String phone;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public UserResponse(String nickName, String phone, Role role, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.nickName = nickName;
        this.phone = phone;
        this.role = role;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
