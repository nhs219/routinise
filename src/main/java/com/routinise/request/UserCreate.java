package com.routinise.request;

import com.routinise.domain.Role;
import com.routinise.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Setter @Getter
public class UserCreate {
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private Role role;

    public UserCreate() {
    }

    @Builder
    public UserCreate(String nickname, String phone, String password, Role role) {
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public User makeUser(PasswordEncoder encoder) {
        return User.builder()
                .nickname(nickname)
                .phone(phone)
                .password(encoder.encode(password))
                .role(role == null? Role.USER : role)
                .build();
    }

}
