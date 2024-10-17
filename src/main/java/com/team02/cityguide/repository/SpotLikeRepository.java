package com.team02.cityguide.repository;

import com.team02.cityguide.entity.SpotLikeEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotLikeRepository extends ListCrudRepository<SpotLikeEntity, Long> {

    List<SpotLikeEntity> findByUserId(Long userId);
    SpotLikeEntity findByUserIdAndSpotId(Long userId, Long spotId);

    @Modifying
    @Query("DELETE FROM spot_likes WHERE user_id = :userId")
    void deleteByUserId(Long userId);
    @Modifying
    @Query("DELETE FROM spot_likes WHERE user_id = :userId AND spot_id = :spotId")
    void deleteByUserIdAndSpotId(@Param("userId") Long userId, @Param("spotId") Long spotId);
}
