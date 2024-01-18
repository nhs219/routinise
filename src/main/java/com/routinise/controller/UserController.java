package com.routinise.controller;

import com.routinise.domain.UserAuthorize;
import com.routinise.response.UserResponse;
import com.routinise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@UserAuthorize
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{uuid}")
    @PreAuthorize("#uuid == authentication.principal.username")
    public UserResponse getUser(@PathVariable String uuid) {
        return userService.getUser(uuid);
    }
}
