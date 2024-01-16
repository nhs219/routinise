package com.routinise.controller;

import com.routinise.response.UserResponse;
import com.routinise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{uuid}")
    public UserResponse getUser(@PathVariable String uuid) {
        return userService.getUser(uuid);
    }
}
