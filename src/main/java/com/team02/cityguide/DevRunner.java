package com.team02.cityguide;

import com.team02.cityguide.external.GoogleApiService;
import com.team02.cityguide.external.RouteRequest;
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
        RouteRequest routeRequest = new RouteRequest(
                new WayPoint("ChIJf5Esy4cUkFQRKK6M06RW2YI"),      // Nordheim Court
                new WayPoint("ChIJV4bOpvcVkFQRJA-LICOZe6Y"),      // Maple hall
                "DRIVE"                                                  // trafficMode
        );
        String routes = googleApiService.computeRoutes(routeRequest);
        logger.info(routes);
    }
}
