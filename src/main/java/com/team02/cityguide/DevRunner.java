package com.team02.cityguide;

import com.team02.cityguide.external.GoogleApiService;
import com.team02.cityguide.external.RouteRequestBody;
import com.team02.cityguide.external.RouteResponseBody;
import com.team02.cityguide.external.WayPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DevRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DevRunner.class);
    private final GoogleApiService googleApiService;

    public DevRunner(GoogleApiService googleApiService) {
        this.googleApiService = googleApiService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RouteRequestBody routeRequestBody = new RouteRequestBody(
                new WayPoint("ChIJf5Esy4cUkFQRKK6M06RW2YI"),      // Nordheim Court
                new WayPoint("ChIJV4bOpvcVkFQRJA-LICOZe6Y"),      // Maple hall
                null,                                                 // trafficMode
                null
        );
        RouteResponseBody routes = googleApiService.computeRoutes(routeRequestBody);
        logger.info("Received route response from google computeRoutes: {}", routes);
    }
}
