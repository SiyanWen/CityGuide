package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;

public record SpotLikeEntity(
    @Id
    Long id,
    Long userId,
    Long spotId
) {
}
