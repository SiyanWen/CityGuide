package com.team02.cityguide.repository;

import com.team02.cityguide.entity.RouteLikeEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface RouteLikeRepository extends ListCrudRepository<RouteLikeEntity, Long> {

    RouteLikeEntity findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM route_likes WHERE user_id = :userId")
    void deleteByUserId(Long userId);
}
