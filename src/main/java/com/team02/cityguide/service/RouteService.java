package com.team02.cityguide.service;

import com.team02.cityguide.entity.CartSpotEntity;
import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.model.RouteDto;
import com.team02.cityguide.model.SurveyBody;
import com.team02.cityguide.repository.CartSpotRepository;
import com.team02.cityguide.repository.RouteRepository;
import com.team02.cityguide.repository.UnitRouteRepository;
import com.team02.cityguide.repository.UserSpotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RouteService {
    private final CartSpotRepository cartSpotRepository;
    private final RouteRepository routeRepository;
    private final UnitRouteRepository unitRouteRepository;
    private final UserSpotRepository userSpotRepository;

    public RouteService(CartSpotRepository cartSpotRepository, RouteRepository routeRepository, UnitRouteRepository unitRouteRepository, UserSpotRepository userSpotRepository) {
        this.cartSpotRepository = cartSpotRepository;
        this.routeRepository = routeRepository;
        this.unitRouteRepository = unitRouteRepository;
        this.userSpotRepository = userSpotRepository;
    }

    @Transactional
    public RouteEntity updateRoute(RouteEntity route) {
        return routeRepository.save(route);  // Ensure this is within a transaction
    }

    // TODO
    public List<RouteDto> planRoute(List<CartSpotEntity> cartSpots, SurveyBody surveyBody) {
        return null;
    }

    // TODO
    private List<Long> permutation() {
        return null;
    }           // List<UserSpotEntityId>
}
