package com.team02.cityguide;

import com.team02.cityguide.external.GoogleApiService;
import com.team02.cityguide.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DevRunner implements ApplicationRunner {
    // TODO
    private static final Logger logger = LoggerFactory.getLogger(DevRunner.class);
    private final GoogleApiService googleApiService;
    private final UserService userService;

    public DevRunner(GoogleApiService googleApiService, UserService userService) {
        this.googleApiService = googleApiService;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        String routes = googleApiService.computeRoutes();
//        logger.info(routes);
        userService.signup("test1@com", "123", "userName", "cityID1", "www.test1.com");
    }
}
