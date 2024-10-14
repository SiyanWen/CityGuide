package com.team02.cityguide.repository;

import com.team02.cityguide.entity.CartSpotEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CartSpotRepository extends ListCrudRepository<CartSpotEntity, Long> {
    List<CartSpotEntity> findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM cart_spots WHERE user_id = :userId")
    void deleteByUserId(Long userId);
}
