package com.team02.cityguide.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RouteResponseBody(
        @JsonProperty("routes")
        List<RouteResponseDto> routes
) {
}
