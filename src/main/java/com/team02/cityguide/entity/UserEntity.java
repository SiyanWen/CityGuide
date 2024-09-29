package com.team02.cityguide.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record UserEntity(
    @Id
    Long id,
    String email,
    String password,
    String userName,
    String profilePicUrl,
    String cityId,
    Boolean enabled
) {
}
