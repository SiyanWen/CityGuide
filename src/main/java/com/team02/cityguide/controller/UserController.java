package com.team02.cityguide.controller;

import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.model.RegisterBody;
import com.team02.cityguide.model.SignInBody;
import com.team02.cityguide.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody RegisterBody body) {
        userService.signup(body.email(), body.password(), body.userName(), body.cityId(), body.profileURL());
    }

//    @PostMapping("/signin")
//    @ResponseStatus(value = HttpStatus.ACCEPTED)
//    public void signIn(@RequestBody SignInBody body) {
//
//    }
}
