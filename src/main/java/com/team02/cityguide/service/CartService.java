package com.team02.cityguide.service;

//import com.team02.cityguide.entity.CartSpotEntity;
import com.team02.cityguide.entity.CartSpots;
import com.team02.cityguide.entity.RouteEntity;
//import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.entity.UserSpots;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.CartDto;
import com.team02.cityguide.repository.*;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private final CartSpotRepository cartSpotRepository;
    private final UserSpotRepository userSpotRepository;
    private final ObjectMapper objectMapper;

    public CartService(CartSpotRepository cartSpotRepository, UserSpotRepository userSpotRepository, ObjectMapper objectMapper) {
        this.cartSpotRepository = cartSpotRepository;
        this.userSpotRepository = userSpotRepository;
        this.objectMapper = objectMapper;
    }

    public CartDto getCart(Long userId) {
        List<CartSpots> cartSpots = cartSpotRepository.findByUserId(userId);
        return new CartDto(userId, cartSpots);
    }

    @Transactional
    public void addSpotToCart(Long userId, AddSpotBody addSpotBody) {
        UserSpots userSpot=userSpotRepository.findByOriginalGid(addSpotBody.originalGid());
        if(userSpot==null) {
            JsonNode openingHoursJson = objectMapper.valueToTree(addSpotBody.openingHours());
            JsonNode typesJson = objectMapper.valueToTree(addSpotBody.types());
            userSpot = new UserSpots(
                    null,
                    addSpotBody.routeId(),
                    addSpotBody.originalGid(),
                    addSpotBody.name(),
                    addSpotBody.address(),
                    addSpotBody.description(),
                    addSpotBody.latitude(),
                    addSpotBody.longitude(),
                    addSpotBody.durationTime(),
                    addSpotBody.cost(),
                    addSpotBody.rating(),
                    addSpotBody.ratingCount(),
                    openingHoursJson, // Corrected JSONB storage
                    typesJson, // Corrected JSONB storage
                    addSpotBody.coverImgUrl(),
                    addSpotBody.reviews()
            );
            userSpotRepository.save(userSpot);
            CartSpots newCartSpot = new CartSpots(
                    null,
                    addSpotBody.originalGid(),
                    userId,
                    addSpotBody.name(),
                    addSpotBody.address(),
                    addSpotBody.rating(),
                    addSpotBody.ratingCount(),
                    addSpotBody.cost(),
                    addSpotBody.durationTime(),
                    openingHoursJson, // Corrected JSONB storage
                    addSpotBody.latitude(),
                    addSpotBody.longitude(),
                    addSpotBody.coverImgUrl()
            );
            cartSpotRepository.save(newCartSpot);
//            userSpot= new UserSpotEntity(null,addSpotBody.routeId(),addSpotBody.originalGid(),addSpotBody.name(),addSpotBody.address(),addSpotBody.description(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.durationTime(),addSpotBody.cost(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.openingHours(),addSpotBody.types(),addSpotBody.coverImgUrl(),addSpotBody.reviews());
//            userSpotRepository.save(userSpot);
//            CartSpotEntity newCartSpot = new CartSpotEntity(null,addSpotBody.originalGid(),userId,addSpotBody.name(),addSpotBody.address(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.cost(),addSpotBody.durationTime(),addSpotBody.openingHours(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.coverImgUrl());
//            cartSpotRepository.save(newCartSpot);
        }else{
            CartSpots cartSpot = cartSpotRepository.findByUserIdAndOriginalGid(userId,addSpotBody.originalGid());
            if (cartSpot == null) {
                CartSpots newCartSpot = new CartSpots(
                        null,
                        addSpotBody.originalGid(),
                        userId,
                        addSpotBody.name(),
                        addSpotBody.address(),
                        addSpotBody.rating(),
                        addSpotBody.ratingCount(),
                        addSpotBody.cost(),
                        addSpotBody.durationTime(),
                        objectMapper.valueToTree(addSpotBody.openingHours()), // 🔥 Corrected JSONB storage
                        addSpotBody.latitude(),
                        addSpotBody.longitude(),
                        addSpotBody.coverImgUrl()
                );
                cartSpotRepository.save(newCartSpot);
//                CartSpotEntity newCartSpot = new CartSpotEntity(null,addSpotBody.originalGid(),userId,addSpotBody.name(),addSpotBody.address(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.cost(),addSpotBody.durationTime(),addSpotBody.openingHours(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.coverImgUrl());
//                cartSpotRepository.save(newCartSpot);
            }
        }
    }

    public void addRouteToCart(Long userId, Long routeId) {
        List<UserSpots> spots = userSpotRepository.findByRouteId(routeId);
        for (UserSpots userSpot : spots) {
            CartSpots cartSpot = cartSpotRepository.findByUserIdAndOriginalGid(userId,userSpot.getOriginalGid());
            if (cartSpot == null) {
                CartSpots newCartSpot = new CartSpots(null, userSpot.getOriginalGid(), userId, userSpot.getName(), userSpot.getAddress(), userSpot.getRating(), userSpot.getRatingCount(), userSpot.getCost(), userSpot.getDurationTime(), userSpot.getOpeningHours(), userSpot.getLatitude(), userSpot.getLongitude(), userSpot.getCoverImgUrl());
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
