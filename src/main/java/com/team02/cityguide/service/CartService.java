package com.team02.cityguide.service;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.CartDto;
import com.team02.cityguide.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartSpotRepository cartSpotRepository;
    private final UserSpotRepository userSpotRepository;
    private final RouteLikeRepository routeLikeRepository;
    private final SpotLikeRepository spotLikeRepository;

    public CartService(CartSpotRepository cartSpotRepository, UserSpotRepository userSpotRepository, RouteLikeRepository routeLikeRepository, SpotLikeRepository spotLikeRepository) {
        this.cartSpotRepository = cartSpotRepository;
        this.userSpotRepository = userSpotRepository;
        this.routeLikeRepository = routeLikeRepository;
        this.spotLikeRepository = spotLikeRepository;
    }

    // TODO
    public CartDto getCart(Long userId) {
        return null;
    }

    // TODO
    public void addSpotToCart(Long userId, AddSpotBody addSpotBody) {

    }

    public void addRouteToCart(Long userId, Long routeId) {

    }

    // TODO
    public void removeSpotFromCart(Long userId, Long spotId) {

    }

    // TODO
    public void clearCart(Long userId) {

    }
}
