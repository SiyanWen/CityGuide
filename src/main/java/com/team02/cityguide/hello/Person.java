package com.team02.cityguide.hello;

public record Person(
        String name,
        String company,
        Address homeAddress,
        Book favoriteBook
) {
}
