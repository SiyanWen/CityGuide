package com.team02.cityguide.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Convert;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
//import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Map;

@Table("cart_spots")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Convert(attributeName = "openingHours", converter = JsonType.class)
public class CartSpots{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String originalGid;
        private Long userId;
        private String name;
        private String address;
        private Double rating;
        private Integer ratingCount;
        private Double cost;
        private Integer durationTime;
//    @JdbcTypeCode(SqlTypes.JSON)
//    Map<String, Object> openingHours,
//    @Convert(attributeName = "openingHours", converter = JsonType.class)
        @Type(JsonType.class)
        @Column(columnDefinition = "jsonb")
        private JsonNode openingHours;
        private Double latitude;
        private Double longitude;
        private String coverImgUrl;
        private Boolean isHotel;
}

//@Table("cart_spots")
////@Convert(attributeName = "openingHours", converter = JsonType.class)
//public record CartSpotEntity(
//    @Id
//    Long id,
//    String originalGid,
//    Long userId,
//    String name,
//    String address,
//    Double rating,
//    Integer ratingCount,
//    Double cost,
//    Integer durationTime,
////    @JdbcTypeCode(SqlTypes.JSON)
////    Map<String, Object> openingHours,
////    @Convert(attributeName = "openingHours", converter = JsonType.class)
//    @Type(JsonType.class)
//    @Column(columnDefinition = "jsonb")
//    JsonNode openingHours,
//    Double latitude,
//    Double longitude,
//    String coverImgUrl
//) {
//}
