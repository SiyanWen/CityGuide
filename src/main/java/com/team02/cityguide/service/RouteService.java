package com.team02.cityguide.service;

import com.team02.cityguide.DevRunner;
import com.team02.cityguide.entity.CartSpotEntity;
import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UnitRouteEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.external.RouteRequestBody;
import com.team02.cityguide.external.RouteResponseBody;
import com.team02.cityguide.external.WayPoint;
import com.team02.cityguide.model.Budget;
import com.team02.cityguide.model.RouteDto;
import com.team02.cityguide.model.SurveyBody;
import com.team02.cityguide.repository.CartSpotRepository;
import com.team02.cityguide.repository.RouteRepository;
import com.team02.cityguide.repository.UnitRouteRepository;
import com.team02.cityguide.repository.UserSpotRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team02.cityguide.external.GoogleApiService;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {
    private final CartSpotRepository cartSpotRepository;
    private final RouteRepository routeRepository;
    private final UnitRouteRepository unitRouteRepository;
    private final UserSpotRepository userSpotRepository;
    private final GoogleApiService googleApiService;
    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);
    public RouteService(
            GoogleApiService googleApiService,
            CartSpotRepository cartSpotRepository,
            RouteRepository routeRepository,
            UnitRouteRepository unitRouteRepository,
            UserSpotRepository userSpotRepository
    ) {
        this.googleApiService = googleApiService;
        this.cartSpotRepository = cartSpotRepository;
        this.routeRepository = routeRepository;
        this.unitRouteRepository = unitRouteRepository;
        this.userSpotRepository = userSpotRepository;
    }

    @Transactional
    public RouteEntity updateRoute(RouteEntity route) {
        return routeRepository.save(route);  // Ensure this is within a transaction
    }

/*
 * SurveyBody structure
 *   daysToPlay
 *   List<StartEndPoints> (size = day + 1)
 *   List<traffic_mode> (size = day)
 *   SpotsPerDay
 *   Budget
 *       food
 *       transport
 *       ticket
 *       total

    public record SurveyBody(
            Integer daysToPlay,
            List<Long> startEndPoints,          // spot_ids
            List<String> trafficModes,
            Integer spotsPerDay,
            Budget budget
    ) {
    }

* */

    // TODO
    public List<RouteDto> planRoute(List<CartSpotEntity> cartSpots, SurveyBody surveyBody) {
        // get 3 plans of spot_ids for each day
        List<List<List<Long>>> plansOfSpotIdsByDay = spotPermutation(cartSpots, surveyBody);
        // build unitRoutes (call Google API for each day's spots) and save to DB;
        for (int i = 0; i < plansOfSpotIdsByDay.size(); i++) { // for each plan
            for (int j = 0; j < plansOfSpotIdsByDay.get(i).size(); j++) { // for each day
                for (int k = 0; k < plansOfSpotIdsByDay.get(i).get(j).size()-1; k++) { // for each starting spot

                }
//                buildUnitRoutes(plansOfSpotIdsByDay.get(i).get(j), surveyBody.budget, i);
            }
        }
        // build routes with that unitRoutes and save to DB;

    }
//        // Tests by Siyan
//        logger.info("got a request to planRoute");
//        // Test google computeRoutes() API
//        RouteRequestBody routeRequestBody = new RouteRequestBody(
//                new WayPoint("ChIJQXY5d3dZwokROz4N4S6oh0M"),      // McDonald
//                new WayPoint("ChIJ2RFUePdYwokRd5R6XF6xFD0"),      // Carnegie hall
//                null,                                                    // trafficMode
//                null
//        );
//        RouteResponseBody routes = googleApiService.computeRoutes(routeRequestBody);
//        logger.info("planned routes01:{}",routes);
//        String dur = routes.routes().get(0).durationTime();
//        dur = dur.substring(0,dur.length() - 1);
//        Integer duration = Integer.parseInt(dur);
//
//        Double distance = (double) routes.routes().get(0).distanceMeters();
//
//        UnitRouteEntity leg01=new UnitRouteEntity(
//                1L,
//                1L,
//                routes.routes().get(0).polyline().encodedPolyline(),
//                1,
//                2L,
//                3L,
//                "DRIVE",0.0, distance,duration);
//
//
//        RouteRequestBody routeRequestBody02 = new RouteRequestBody(
//                new WayPoint("ChIJ2RFUePdYwokRd5R6XF6xFD0"),      // McDonald
//                new WayPoint("ChIJ9U1mz_5YwokRosza1aAk0jM"),      // Carnegie hall
//                null,                                                 // trafficMode
//                null
//        );
//        RouteResponseBody routes02 = googleApiService.computeRoutes(routeRequestBody02);
//        logger.info("planned routes02:{}",routes02);
//        String dur02 = routes02.routes().get(0).durationTime();
//        dur02 = dur02.substring(0,dur02.length() - 1);
//        Integer duration02 = Integer.parseInt(dur02);
//
//        Double distance02 = (double) routes02.routes().get(0).distanceMeters();
//
//        UnitRouteEntity leg02=new UnitRouteEntity(
//                1L,
//                1L,
//                routes02.routes().get(0).polyline().encodedPolyline(),
//                3,
//                3L,
//                4L,
//                "DRIVE",0.0, distance02,duration02);
//        List<UnitRouteEntity> legs = new ArrayList<>();
//        legs.add(leg01);
//        legs.add(leg02);
//        Double distanceOfRoute=distance+distance02;
//        RouteDto route01= new RouteDto(
//                1L,
//                1L,
//                null,
//                null,
//                distanceOfRoute,
//                "DRIVE",0.0,1,legs);
//        List<RouteDto> threeRoutes= new ArrayList<>();
//        threeRoutes.add(route01);
//        return threeRoutes;


    // TODO
    // Each day: List<CartSpotEntityId>
    // All days: List<List<CartSpotEntityId>>
    // 3 plans: List<List<List<CartSpotEntityId>>>; at least the first one is not null;
    private List<List<List<Long>>> spotPermutation(List<CartSpotEntity> cartSpots, SurveyBody surveyBody) {

        return null;
    }
}
