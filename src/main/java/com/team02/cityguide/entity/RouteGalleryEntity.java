package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("route_galleries")
public record RouteGalleryEntity(
    @Id
    Long id,
    Long userId,
    String name
) {
}
