package com.team02.cityguide.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RouteResponseDto(
    @JsonProperty("distanceMeters")
    int distanceMeters,
    @JsonProperty("duration")
    String durationTime,
    Polyline polyline,
    String description
) {
}
