package com.team02.cityguide.model;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.databind.JsonNode;
//import jakarta.persistence.*;
//import lombok.*;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public record AddSpotBody(
        Long spotId,
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
        JsonNode openingHours,
        JsonNode types,
        String coverImgUrl,
        String reviews
) {
}
