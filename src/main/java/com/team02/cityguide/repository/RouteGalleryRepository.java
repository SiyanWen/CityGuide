package com.team02.cityguide.repository;

import com.team02.cityguide.entity.RouteGalleryEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RouteGalleryRepository extends ListCrudRepository<RouteGalleryEntity, Long> {

    RouteGalleryEntity findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM route_galleries WHERE user_id = :userId")
    void deleteByUserId(Long userId);
}
