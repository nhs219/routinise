package com.routinise.controller;

import com.routinise.domain.UserAuthorize;
import com.routinise.request.UserUpdate;
import com.routinise.response.UserResponse;
import com.routinise.service.UserService;
import com.routinise.utils.SecurityUtils;
import io.jsonwebtoken.Header;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@UserAuthorize
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/mypage")
    public UserResponse getUser(Authentication authentication) {
        String userId = SecurityUtils.getUserId(authentication);

        return userService.getUser(userId);
    }

    @PutMapping
    public void updateUser(@RequestBody @Valid UserUpdate userUpdate, Authentication authentication) {
        String userId = SecurityUtils.getUserId(authentication);

        userService.updateUser(userUpdate, userId);
    }
}
