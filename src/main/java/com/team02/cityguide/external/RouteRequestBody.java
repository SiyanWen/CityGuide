package com.team02.cityguide.external;

public record RouteRequestBody(
    WayPoint origin,
    WayPoint destination,
    String travelMode,
    Boolean computeAlternativeRoutes
) {
}
