package com.team02.cityguide;

import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.repository.CartSpotRepository;
import com.team02.cityguide.repository.RouteRepository;
import com.team02.cityguide.repository.UnitRouteRepository;
import com.team02.cityguide.repository.UserSpotRepository;
import com.team02.cityguide.service.RouteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTests {
    @Mock
    private CartSpotRepository cartSpotRepository;

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private UnitRouteRepository unitRouteRepository;

    @Mock
    private UserSpotRepository userSpotRepository;

    private RouteService routeService;


    @Test
    void planRouteTest_(){

    }
}
