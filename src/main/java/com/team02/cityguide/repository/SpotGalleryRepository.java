package com.team02.cityguide.repository;

import com.team02.cityguide.entity.SpotGalleryEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface SpotGalleryRepository extends ListCrudRepository<SpotGalleryEntity, Long> {

    SpotGalleryEntity findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM spot_galleries WHERE user_id = :userId")
    void deleteByUserId(Long userId);
}
