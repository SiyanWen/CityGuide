package com.team02.cityguide;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.laioffer.onlineorder.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class DevRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DevRunner.class);


    private final UserService userService;


    public DevRunner(
           UserService userService) {
       this.userService = userService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerService.signUp("foo@mail.com", "123456", "Foo", "Bar");
    }
}
