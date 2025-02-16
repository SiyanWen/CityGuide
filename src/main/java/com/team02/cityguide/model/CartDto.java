package com.team02.cityguide.model;

import com.team02.cityguide.entity.CartSpots;
import com.team02.cityguide.entity.CartSpots;
import com.team02.cityguide.entity.UserEntity;

import java.util.List;

public record CartDto(
    Long userId,
    List<CartSpots> cartSpots
) {
    public CartDto(UserEntity userEntity, List<CartSpots> cartSpots) {
        this(userEntity.id(), cartSpots);
    }
}
