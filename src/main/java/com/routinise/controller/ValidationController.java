package com.routinise.controller;

import com.routinise.request.NickNameCheck;
import com.routinise.service.ValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/valid")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping
    public void checkNickName(@RequestBody @Valid NickNameCheck nickName) {
        validationService.checkDuplicateNickName(nickName);
    }
}
