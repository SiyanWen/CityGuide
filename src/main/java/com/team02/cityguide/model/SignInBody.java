package com.team02.cityguide.model;

public record SignInBody(
        String email,
        String password,
        String username
) {
}
