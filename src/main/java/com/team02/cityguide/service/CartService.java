package com.team02.cityguide.service;

import com.team02.cityguide.entity.CartSpotEntity;
import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.CartDto;
import com.team02.cityguide.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private final CartSpotRepository cartSpotRepository;
    private final UserSpotRepository userSpotRepository;

    public CartService(CartSpotRepository cartSpotRepository, UserSpotRepository userSpotRepository) {
        this.cartSpotRepository = cartSpotRepository;
        this.userSpotRepository = userSpotRepository;
    }

    // TODO
    public CartDto getCart(Long userId) {
        List<CartSpotEntity> cartSpots = cartSpotRepository.findByUserId(userId);
        return new CartDto(userId, cartSpots);
    }

    // TODO
    public void addSpotToCart(Long userId, AddSpotBody addSpotBody) {
        UserSpotEntity userSpot=userSpotRepository.findByOriginalGid(addSpotBody.originalGid());
        if(userSpot==null) {
            userSpot= new UserSpotEntity(null,addSpotBody.routeId(),addSpotBody.originalGid(),addSpotBody.name(),addSpotBody.address(),addSpotBody.description(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.durationTime(),addSpotBody.cost(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.openingHours(),addSpotBody.types(),addSpotBody.coverImgUrl(),addSpotBody.reviews());
            userSpotRepository.save(userSpot);
            CartSpotEntity newCartSpot = new CartSpotEntity(null,addSpotBody.originalGid(),userId,addSpotBody.name(),addSpotBody.address(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.cost(),addSpotBody.durationTime(),addSpotBody.openingHours(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.coverImgUrl());
            cartSpotRepository.save(newCartSpot);
        }else{
            CartSpotEntity cartSpot = cartSpotRepository.findByUserIdAndOriginalGid(userId,addSpotBody.originalGid());
            if (cartSpot == null) {
                CartSpotEntity newCartSpot = new CartSpotEntity(null,addSpotBody.originalGid(),userId,addSpotBody.name(),addSpotBody.address(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.cost(),addSpotBody.durationTime(),addSpotBody.openingHours(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.coverImgUrl());
                cartSpotRepository.save(newCartSpot);

            }
        }
    }

    public void addRouteToCart(Long userId, Long routeId) {
        List<UserSpotEntity> spots = userSpotRepository.findByRouteId(routeId);
        for (UserSpotEntity userSpot : spots) {
            CartSpotEntity cartSpot = cartSpotRepository.findByUserIdAndOriginalGid(userId,userSpot.originalGid());
            if (cartSpot == null) {
                CartSpotEntity newCartSpot = new CartSpotEntity(null, userSpot.originalGid(), userId, userSpot.name(), userSpot.address(), userSpot.rating(), userSpot.ratingCount(), userSpot.cost(), userSpot.durationTime(), userSpot.openingHours(), userSpot.latitude(), userSpot.longitude(), userSpot.coverImgUrl());
                cartSpotRepository.save(newCartSpot);
            }
        }
    }

    // TODO
    public void removeSpotFromCart(Long userId, Long spotId) {
        cartSpotRepository.deleteByIdAndUserId(userId, spotId);
    }

    // TODO
    public void clearCart(Long userId) {
        cartSpotRepository.deleteByUserId(userId);
    }
}
