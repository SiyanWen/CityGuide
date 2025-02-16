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

import java.util.List;
import java.util.Map;

@Table("user_spots")
//@Convert(attributeName = "openingHours", converter = JsonType.class)
//@Convert(attributeName = "types", converter = JsonType.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSpots{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long routeId;
        private String originalGid;
        private String name;
        private String address;
        private String description;
        private Double latitude;
        private Double longitude;
        private Integer durationTime;
        private Double cost;
        private Double rating;
        private Integer ratingCount;
        @Type(JsonType.class)
        @Column(columnDefinition = "jsonb")
        private JsonNode openingHours;
//        @JdbcTypeCode(SqlTypes.JSON)
//        Map<String, Object> openingHours,        // how to save to jsonb: map
        @Type(JsonType.class)                   // Hibernate will handle JSONB storage
        @Column(columnDefinition = "jsonb")     // Ensure JSONB type in PostgreSQL
        private JsonNode types;
//        @JdbcTypeCode(SqlTypes.JSON)
//        Map<String, List<String>> types,
        private String coverImgUrl;
        private String reviews;
}

//@Table("user_spots")
////@Convert(attributeName = "openingHours", converter = JsonType.class)
////@Convert(attributeName = "types", converter = JsonType.class)
//public record UserSpots(
//        @Id
//        Long id,
//        Long routeId,
//        String originalGid,
//        String name,
//        String address,
//        String description,
//        Double latitude,
//        Double longitude,
//        Integer durationTime,
//        Double cost,
//        Double rating,
//        Integer ratingCount,
//        @Type(JsonType.class)
//        @Column(columnDefinition = "jsonb")
//        JsonNode openingHours,
////        @JdbcTypeCode(SqlTypes.JSON)
////        Map<String, Object> openingHours,        // how to save to jsonb: map
//        @Type(JsonType.class)                   // Hibernate will handle JSONB storage
//        @Column(columnDefinition = "jsonb")     // Ensure JSONB type in PostgreSQL
//        JsonNode types,
////        @JdbcTypeCode(SqlTypes.JSON)
////        Map<String, List<String>> types,
//        String coverImgUrl,
//        String reviews
//) {
//}
