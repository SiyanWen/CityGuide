package com.team02.cityguide.external;

public record RouteRequest(
    String startPlaceId,
    String endPlaceId,
    String mode
) {
}
