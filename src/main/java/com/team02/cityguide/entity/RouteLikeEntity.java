package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;

public record RouteLikeEntity(
    @Id
    Long id,
    Long userId,
    Long routeId
) {
}
