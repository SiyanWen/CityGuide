package com.team02.cityguide.service;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.repository.CartRepository;
import com.team02.cityguide.repository.RouteRepository;
import com.team02.cityguide.repository.UnitRouteRepository;
import com.team02.cityguide.repository.UserSpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    private final CartRepository cartRepository;
    private final RouteRepository routeRepository;
    private final UnitRouteRepository unitRouteRepository;
    private final UserSpotRepository userSpotRepository;

    public RouteService(CartRepository cartRepository, RouteRepository routeRepository, UnitRouteRepository unitRouteRepository, UserSpotRepository userSpotRepository) {
        this.cartRepository = cartRepository;
        this.routeRepository = routeRepository;
        this.unitRouteRepository = unitRouteRepository;
        this.userSpotRepository = userSpotRepository;
    }

    // TODO
    public List<RouteEntity> planRoute() {
        return null;
    }

    // TODO
    private List<UserSpotEntity> permutation() {
        return null;
    }
}
