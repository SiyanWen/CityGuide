package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("spot_likes")
public record SpotLikeEntity(
    @Id
    Long id,
    Long userId,
    Long spotId
) {
}
