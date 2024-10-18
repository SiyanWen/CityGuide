package com.team02.cityguide.model;


public record RegisterBody(
     String email,
     String password,
     String userName,
     String cityId
) {
}
