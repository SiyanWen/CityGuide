package com.team02.cityguide.repository;

import com.team02.cityguide.entity.SpotLikeEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface SpotLikeRepository extends ListCrudRepository<SpotLikeEntity, Long> {

    SpotLikeEntity findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM spot_likes WHERE user_id = :userId")
    void deleteByUserId(Long userId);
}
