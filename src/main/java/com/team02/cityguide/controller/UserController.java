package com.team02.cityguide.controller;


import com.team02.cityguide.model.RegisterBody;
import com.team02.cityguide.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


 private final UserService UserService;


 public UserController(CustomerService userService) {
 this.userService = userService;
 }


 @PostMapping("/signup")
 @ResponseStatus(value = HttpStatus.CREATED)
 public void signUp(@RequestBody RegisterBody body) {
 userService.signUp(body.email(), body.password(), body.firstName(), body.lastName());
 }
}
