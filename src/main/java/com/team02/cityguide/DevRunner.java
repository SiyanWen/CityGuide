package com.team02.cityguide;

import com.team02.cityguide.external.GoogleApiService;
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

    public DevRunner(GoogleApiService googleApiService) {
        this.googleApiService = googleApiService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String routes = googleApiService.computeRoutes();
        logger.info(routes);
    }
}
