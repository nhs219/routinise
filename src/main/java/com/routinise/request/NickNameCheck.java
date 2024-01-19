package com.routinise.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class NickNameCheck {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    @Builder
    public NickNameCheck(String nickName) {
        this.nickName = nickName;
    }
}
