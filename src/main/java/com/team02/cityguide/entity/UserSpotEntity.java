package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
    String imageUrl,
    Double latitude,
    Double longitude,
    Integer durationTime,
    Double cost,
    Double rating,
    Integer ratingCount,
    String openTime,
    String closeTime,
    String reviews
) {
}
