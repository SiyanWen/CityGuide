package com.team02.cityguide;

import com.team02.cityguide.external.GoogleApiService;
import com.team02.cityguide.external.RouteRequestBody;
import com.team02.cityguide.external.RouteResponseBody;
import com.team02.cityguide.external.WayPoint;
import com.team02.cityguide.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DevRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DevRunner.class);
    private final GoogleApiService googleApiService;
    private final UserService userService;

    public DevRunner(GoogleApiService googleApiService, UserService userService) {
        this.googleApiService = googleApiService;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Test google computeRoutes() API
        RouteRequestBody routeRequestBody = new RouteRequestBody(
                new WayPoint("ChIJf5Esy4cUkFQRKK6M06RW2YI"),      // Nordheim Court
                new WayPoint("ChIJV4bOpvcVkFQRJA-LICOZe6Y"),      // Maple hall
                null,                                                 // trafficMode
                null
        );
        RouteResponseBody routes = googleApiService.computeRoutes(routeRequestBody);
        logger.info("Received route response from google computeRoutes: {}", routes);

        // Test user signUp
        userService.signup("foo@mail.com", "123456", "Foo", "San Francisco");
    }
}
