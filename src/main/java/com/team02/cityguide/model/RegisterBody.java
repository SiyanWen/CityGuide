package com.team02.cityguide.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterBody(
        String email,
        String password,
        @JsonProperty("user_name") String userName,
        @JsonProperty("city_id") String cityId,
        @JsonProperty("profile_url") String profileURL
) {
}
