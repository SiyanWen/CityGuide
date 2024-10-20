package com.team02.cityguide.model;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UnitRouteEntity;

import java.util.List;

public record RouteDto(     // one route plan for all days
    Long id,
//    Long creatorId,
    String name,
    String description,
    Double distance,
    String trafficMode,
    Double budget,
    Integer durationTime,
    List<UnitRouteEntity> legs
) {
    public RouteDto(RouteEntity routeEntity, List<UnitRouteEntity> legs) {
        this(
                routeEntity.id(),
//                routeEntity.creatorId(),
                routeEntity.name(),
                routeEntity.description(),
                routeEntity.distance(),
                routeEntity.trafficMode(),
                routeEntity.budget(),
                routeEntity.durationTime(),
                legs
        );
    }
}
