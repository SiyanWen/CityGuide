package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("spot_galleries")
public record SpotGalleryEntity(
    @Id
    Long id,
    Long userId,
    String name
) {
}
