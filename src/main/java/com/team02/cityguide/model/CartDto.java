package com.team02.cityguide.model;

import com.team02.cityguide.entity.CartSpotEntity;
import com.team02.cityguide.entity.UserEntity;

import java.util.List;

public record CartDto(
    Long userId,
    List<CartSpotEntity> cartSpots
) {
    public CartDto(UserEntity userEntity, List<CartSpotEntity> cartSpots) {
        this(userEntity.id(), cartSpots);
    }
}
