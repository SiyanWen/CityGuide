package com.team02.cityguide.model;

import java.util.List;
import java.util.Map;

public record AddSpotBody(
        Long spotId,
        Long routeId,
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
        Map<String, Object>  openingHours,      // how to save to jsonb: map
        Map<String, List<String>> types,
        String coverImgUrl,
        String reviews
) {
}
