package com.team02.cityguide.controller;


import com.team02.cityguide.model.RegisterBody;
import com.team02.cityguide.model.UserDto;
import com.team02.cityguide.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class UserController {


     private final UserService userService;


     public UserController(UserService userService) {
         this.userService = userService;
     }

     @GetMapping("/user/info")
     public UserDto getUserInfo() {
         String email = getCurrentAuthenticatedUserEmail();
         return userService.getUserInfo(email);
     }

     private String getCurrentAuthenticatedUserEmail() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         return authentication.getName();  // Assuming the email is used as the username
     }


     @PostMapping("/signup")
     @ResponseStatus(value = HttpStatus.CREATED)
     public void signUp(@RequestBody RegisterBody body) {
         userService.signup(body.email(), body.password(), body.userName(), body.cityId());
     }

     // Sign out function
     @PostMapping("/signout")
     @ResponseStatus(value = HttpStatus.OK)
     public void signOut() {
         // Clear the SecurityContext to effectively log out the user
         SecurityContextHolder.clearContext();
     }
}
