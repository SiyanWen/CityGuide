package com.team02.cityguide.model;

import com.team02.cityguide.entity.SpotLikeEntity;
import com.team02.cityguide.entity.UserEntity;
//import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.entity.UserSpots;

import java.util.List;

public record SpotGalleryDto(
    Long userId,
    List<UserSpots> spotLikes
) {
    public SpotGalleryDto(UserEntity userEntity, List<UserSpots> userSpots) {
        this(userEntity.id(), userSpots);
    }
}
