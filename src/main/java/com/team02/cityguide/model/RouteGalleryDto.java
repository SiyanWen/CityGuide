package com.team02.cityguide.model;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.RouteLikeEntity;
import com.team02.cityguide.entity.UserEntity;

import java.util.List;

public record RouteGalleryDto(  // ifMyTripPlan is written inside the service
    Long userId,
    List<RouteEntity> routeLikes
) {
    public RouteGalleryDto(UserEntity userEntity, List<RouteEntity> routes) {
        this(userEntity.id(), routes);
    }
}
