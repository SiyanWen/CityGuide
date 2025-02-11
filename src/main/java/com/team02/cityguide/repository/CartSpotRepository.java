package com.team02.cityguide.repository;

import com.team02.cityguide.entity.CartSpotEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartSpotRepository extends ListCrudRepository<CartSpotEntity, Long> {
    CartSpotEntity findByUserIdAndOriginalGid(Long userId, String originalGid) ;

    List<CartSpotEntity> findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM cart_spots WHERE user_id = :userId")
    void deleteByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM cart_spots WHERE id = :spotId AND user_id = :userId")
    void deleteByIdAndUserId(@Param("userId") Long userId, @Param("spotId") Long spotId);

}
