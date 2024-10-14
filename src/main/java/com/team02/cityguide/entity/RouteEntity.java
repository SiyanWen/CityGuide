package com.team02.cityguide.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("routes")
public record RouteEntity(
    @Id
    Long id,
    Long creatorId,
    String name,
    String description,
    Double distance,
    String trafficMode,
    Double budget,
    Integer durationTime
) {
}
