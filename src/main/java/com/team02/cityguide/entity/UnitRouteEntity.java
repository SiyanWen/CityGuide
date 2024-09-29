package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("unit_routes")
public record UnitRouteEntity(
    @Id
    Long id,
    Long routeId,
    String googlePolylineId,
    Long startspotId,
    Long endpointId,
    String trafficMode,
    Double budget,
    Double distance,
    Integer durationTime
) {
}
