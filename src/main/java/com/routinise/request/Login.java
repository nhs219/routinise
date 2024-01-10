package com.routinise.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Login {

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public Login() {}

    @Builder
    public Login(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
