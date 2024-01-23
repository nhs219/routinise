package com.routinise.service;

import com.routinise.request.NickNameCheck;

public interface ValidationService {
    void checkDuplicateNickName(NickNameCheck nickName);
}
