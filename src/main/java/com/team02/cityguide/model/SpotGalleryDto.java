package com.team02.cityguide.model;

import com.team02.cityguide.entity.SpotLikeEntity;
import com.team02.cityguide.entity.UserEntity;

import java.util.List;

public record SpotGalleryDto(
    Long userId,
    List<SpotLikeEntity> spotLikes
) {
    public SpotGalleryDto(UserEntity userEntity, List<SpotLikeEntity> spotLikes) {
        this(userEntity.id(), spotLikes);
    }
}
