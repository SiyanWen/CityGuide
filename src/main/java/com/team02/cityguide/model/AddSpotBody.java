package com.team02.cityguide.model;

public record AddSpotBody(
    Long spotId,
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
