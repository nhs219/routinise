package com.routinise.request;

import com.routinise.domain.Role;
import com.routinise.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Setter @Getter
public class Signup {
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "잘못된 이메일 형식입니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$",
    message = "영문과 숫자가 포함된 8자리 이상의 비밀번호를 입력해주세요.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private Role role;

    @Builder
    public Signup(String nickname, String email, String password, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User makeUser(PasswordEncoder encoder) {
        return User.builder()
                .nickname(nickname)
                .email(email)
                .password(encoder.encode(password))
                .role(role == null? Role.USER : role)
                .build();
    }

}
