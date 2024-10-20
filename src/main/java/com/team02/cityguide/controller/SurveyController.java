package com.team02.cityguide.controller;

import com.team02.cityguide.entity.CartSpotEntity;
import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.model.RouteDto;
import com.team02.cityguide.model.SurveyBody;
import com.team02.cityguide.service.CartService;
import com.team02.cityguide.service.GalleryService;
import com.team02.cityguide.service.RouteService;
import com.team02.cityguide.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurveyController {
    private final RouteService routeService;
    private final CartService cartService;
    private final UserService userService;

    public SurveyController(RouteService routeService, CartService cartService, UserService userService) {
        this.routeService = routeService;
        this.cartService = cartService;
        this.userService = userService;
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
    public List<RouteDto> submitSurvey(@AuthenticationPrincipal User user, @RequestBody SurveyBody surveyBody) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        // use user_id to get cart_spots
        List<CartSpotEntity> cartSpots = cartService.getCart(userEntity.id()).cartSpots();
        // plan() - use cart_spots to get routes
        List<RouteDto> routes = routeService.planRoute(cartSpots, surveyBody);
        return routes;
    }
}
