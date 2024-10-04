package com.team02.cityguide.controller;

import com.team02.cityguide.service.CartService;
import com.team02.cityguide.service.GalleryService;
import com.team02.cityguide.service.RouteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurveyController {
    private final RouteService routeService;
    private final CartService cartService;

    public SurveyController(RouteService routeService, CartService cartService) {
        this.routeService = routeService;
        this.cartService = cartService;
    }

    /*
    * SurveyBody structure
    *   duration
    *   List<StartEndPoints> (size = day + 1)
    *   List<traffic_mode> (size = day)
    *   SpotsPerDay
    *   Budget
    *       food
    *       transport
    *       ticket
    *       total
    * */

    @PostMapping("/survey")
    public List<RouteDto> submitSurvey(@RequestBody SurveyBody surveyBody) {
        // use user_id to get cart_spots
        // plan()
    }
}
