package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("cart_spots")
public record CartSpotEntity(
    @Id
    Long id,
    String originalGid,
    Long userId,
    String name,
    String address,
    Double rating,
    Integer ratingCount,
    Double cost,
    Integer durationTime,
    String openingHours,
    Double latitude,
    Double longitude,
    String coverImgUrl
) {
}
