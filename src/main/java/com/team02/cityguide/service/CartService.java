package com.team02.cityguide.service;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserSpotRepository userSpotRepository;
    private final RouteGalleryRepository routeGalleryRepository;
    private final SpotGalleryRepository spotGalleryRepository;

    public CartService(CartRepository cartRepository, UserSpotRepository userSpotRepository, RouteGalleryRepository routeGalleryRepository, SpotGalleryRepository spotGalleryRepository) {
        this.cartRepository = cartRepository;
        this.userSpotRepository = userSpotRepository;
        this.routeGalleryRepository = routeGalleryRepository;
        this.spotGalleryRepository = spotGalleryRepository;
    }

    // TODO
    public List<UserSpotEntity> getCart() {
        return null;
    }

    // TODO
    public void addSpotToCart(UserSpotEntity userSpotEntity) {

    }

    public void addRouteToCart(RouteEntity routeEntity) {

    }

    // TODO
    public void removeSpotFromCart(UserSpotEntity userSpotEntity) {

    }
}
