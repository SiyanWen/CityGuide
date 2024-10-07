package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("route_likes")
public record RouteLikeEntity(
    @Id
    Long id,
    Long userId,
    Long routeId
) {
}
