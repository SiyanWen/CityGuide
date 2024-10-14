package com.team02.cityguide.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Convert;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("cart_spots")
@Convert(attributeName = "openingHours", converter = JsonType.class)
public record CartSpotEntity(
    @Id
    Long id,
    String originalGid,
    Long userId,
    String name,
    String address,
    Double rating,
    Integer ratingCount,
    Double cost,
    Integer durationTime,
    @JdbcTypeCode(SqlTypes.JSON)
    String openingHours,
    Double latitude,
    Double longitude,
    String coverImgUrl
) {
}
