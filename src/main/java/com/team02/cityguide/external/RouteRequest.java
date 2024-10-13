package com.team02.cityguide.external;

public record RouteRequest(
    WayPoint origin,
    WayPoint destination,
    String travelMode
) {
}
