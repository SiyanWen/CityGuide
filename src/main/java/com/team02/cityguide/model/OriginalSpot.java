package com.team02.cityguide.model;

public record OriginalSpot(
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
