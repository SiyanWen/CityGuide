package com.team02.cityguide.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Convert;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Map;

@Table("user_spots")
@Convert(attributeName = "openingHours", converter = JsonType.class)
@Convert(attributeName = "types", converter = JsonType.class)
public record UserSpotEntity(
        @Id
        Long id,
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
        @JdbcTypeCode(SqlTypes.JSON)
        Map<String, Object> openingHours,      // how to save to jsonb: map
        @JdbcTypeCode(SqlTypes.JSON)
        Map<String, List<String>> types,
        String coverImgUrl,
        String reviews
) {
}
