package com.team02.cityguide.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Polyline(
        @JsonProperty("encodedPolyline")
        String encodedPolyline
) {
}
