package com.team02.cityguide.model;

import com.team02.cityguide.entity.RouteLikeEntity;
import com.team02.cityguide.entity.UserEntity;

import java.util.List;

public record RouteGalleryDto(  // ifMyTripPlan is written inside the service
    Long userId,
    List<RouteLikeEntity> routeLikes
) {
    public RouteGalleryDto(UserEntity userEntity, List<RouteLikeEntity> routeLikes) {
        this(userEntity.id(), routeLikes);
    }
}
