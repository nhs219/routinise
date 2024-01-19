package com.routinise.request;

import com.routinise.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter @ToString
public class UserUpdate {

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$",
            message = "영문과 숫자가 포함된 8자리 이상의 비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    public void userForUpdate(User user, PasswordEncoder encoder) {
        user.updateUser(phone, encoder.encode(password), nickName);
    }
}
