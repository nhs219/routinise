package com.routinise.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Login {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "잘못된 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Builder
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
