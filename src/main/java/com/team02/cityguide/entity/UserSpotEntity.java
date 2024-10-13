package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("user_spots")
public record UserSpotEntity(
    @Id
    Long id,
    Long routeId,
    Long cartId,
    Long galleryId,
    String originalGid,
    String name,
    String address,
    String description,
    Double latitude,
    Double longitude,
    Integer durationTime,
    Double cost,
    Double rating,
    Integer ratingCount,
    String openingHours,      // how to save to jsonb: map
    String type,
    String coverImgUrl,
    String reviews
) {
}
