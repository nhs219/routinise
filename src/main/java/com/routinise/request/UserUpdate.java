package com.routinise.request;

import jakarta.validation.constraints.Pattern;

public class UserUpdate {

    private String phone;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$",
            message = "영문과 숫자가 포함된 8자리 이상의 비밀번호를 입력해주세요.")
    private String password;

    private String nickName;
}
