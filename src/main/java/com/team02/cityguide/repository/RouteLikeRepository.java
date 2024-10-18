package com.team02.cityguide.repository;

import com.team02.cityguide.entity.RouteLikeEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteLikeRepository extends ListCrudRepository<RouteLikeEntity, Long> {

    List<RouteLikeEntity> findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM route_likes WHERE user_id = :userId")
    void deleteByUserId(Long userId);
    @Modifying
    @Query("DELETE FROM route_likes WHERE user_id = :userId AND route_id = :routeId")
    void deleteByUserIdAndRouteId(@Param("userId") Long userId, @Param("routeId") Long routeId);
}
