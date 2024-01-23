package com.routinise.service;

import com.routinise.exception.Duplicate;
import com.routinise.repository.UserRepository;
import com.routinise.request.NickNameCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService{
    private final UserRepository userRepository;

    @Override
    public void checkDuplicateNickName(NickNameCheck nickNameCheck) {
        String nickName = nickNameCheck.getNickName();
        if (userRepository.countByNickname(nickName) > 0) {
            throw new Duplicate("이미 사용중인 닉네임입니다.");
        }
    }
}
