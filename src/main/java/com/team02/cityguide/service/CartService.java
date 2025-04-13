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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.team02.cityguide.DevRunner.logger;

@Service
public class CartService {
    private final CartSpotRepository cartSpotRepository;
    private final UserSpotRepository userSpotRepository;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

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
        Boolean isHotel = false;
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
            // check if spot type is within ["lodging", "real_estate_agency"], true -> isHotel = true;
            // logger.info("Spot types: " + typesJson);
            // logger.info("Spot types to string: " + typesJson.toString());
            if (typesJson == null) return;
            for (JsonNode type : typesJson) {
                // logger.info("Spot type's type: " + type.getClass());
                if (type.asText().equals("lodging") || type.asText().equals("real_estate_agency")) {
                    isHotel = true;
                    break;
                }
            }
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
                    addSpotBody.coverImgUrl(),
                    isHotel
            );
            cartSpotRepository.save(newCartSpot);
//            userSpot= new UserSpotEntity(null,addSpotBody.routeId(),addSpotBody.originalGid(),addSpotBody.name(),addSpotBody.address(),addSpotBody.description(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.durationTime(),addSpotBody.cost(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.openingHours(),addSpotBody.types(),addSpotBody.coverImgUrl(),addSpotBody.reviews());
//            userSpotRepository.save(userSpot);
//            CartSpotEntity newCartSpot = new CartSpotEntity(null,addSpotBody.originalGid(),userId,addSpotBody.name(),addSpotBody.address(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.cost(),addSpotBody.durationTime(),addSpotBody.openingHours(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.coverImgUrl());
//            cartSpotRepository.save(newCartSpot);
        }else{
            CartSpots cartSpot = cartSpotRepository.findByUserIdAndOriginalGid(userId,addSpotBody.originalGid());
            if (cartSpot == null) {
                // check if spot type is within ["lodging", "real_estate_agency"], true -> isHotel = true;
                if (userSpot.getTypes() != null) {
                    for (JsonNode type : userSpot.getTypes()) {
                        if (type.asText().equals("lodging") || type.asText().equals("real_estate_agency")) {
                            isHotel = true;
                            break;
                        }
                    }
                }
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
                        addSpotBody.coverImgUrl(),
                        isHotel
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
                // check ifHotel later
                CartSpots newCartSpot = new CartSpots(null, userSpot.getOriginalGid(), userId, userSpot.getName(), userSpot.getAddress(), userSpot.getRating(), userSpot.getRatingCount(), userSpot.getCost(), userSpot.getDurationTime(), userSpot.getOpeningHours(), userSpot.getLatitude(), userSpot.getLongitude(), userSpot.getCoverImgUrl(), false);
                cartSpotRepository.save(newCartSpot);
            }
        }
    }

    // TODO
    @Transactional
    public void removeSpotFromCart(Long spotId, Long userId) {
//        logger.info("Remove spot from cart at CartService: " + userId + " " + spotId);
        cartSpotRepository.deleteByIdAndUserId(spotId, userId);
    }

    // TODO
    @Transactional
    public void clearCart(Long userId) {
        cartSpotRepository.deleteByUserId(userId);
    }
}
