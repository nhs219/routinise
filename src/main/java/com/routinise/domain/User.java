package com.routinise.domain;

import com.routinise.response.LoginResponse;
import com.routinise.response.UserResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String userId;

    private String nickname;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String nickname, String phone, String password, Role role) {
        this.userId = UUID.randomUUID().toString();
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public LoginResponse makeLoginResponse(String token) {
        return LoginResponse.builder()
                .nickName(nickname)
                .role(role)
                .token(token)
                .build();
    }

    public UserResponse makeUserResponse() {
        return UserResponse.builder()
                .nickName(nickname)
                .phone(phone)
                .role(role)
                .createdDate(this.getCreatedDateTime())
                .updatedDate(this.getUpdatedDateTime())
                .build();
    }

    public void updateUser(String phone, String password, String nickname) {
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
    }
}
