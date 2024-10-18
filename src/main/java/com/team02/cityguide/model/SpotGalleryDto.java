package com.team02.cityguide.model;

import com.team02.cityguide.entity.SpotLikeEntity;
import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.entity.UserSpotEntity;

import java.util.List;

public record SpotGalleryDto(
    Long userId,
    List<UserSpotEntity> spotLikes
) {
    public SpotGalleryDto(UserEntity userEntity, List<UserSpotEntity> userSpots) {
        this(userEntity.id(), userSpots);
    }
}
