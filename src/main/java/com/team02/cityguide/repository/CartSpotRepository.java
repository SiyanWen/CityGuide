package com.team02.cityguide.repository;

import com.team02.cityguide.entity.CartSpots;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartSpotRepository extends ListCrudRepository<CartSpots, Long> {
    CartSpots findByUserIdAndOriginalGid(Long userId, String originalGid) ;

    List<CartSpots> findByUserId(Long userId);

//    These annotations are from JPA and not useful since we already used the ListCrudRepository from JDBC
//    @Modifying
//    @Query("DELETE FROM cart_spots WHERE user_id = :userId")
    void deleteByUserId(Long userId);

//    @Modifying
//    @Query("DELETE FROM cart_spots WHERE id = :spotId AND user_id = :userId")
    void deleteByIdAndUserId(@Param("id") Long spotId, @Param("userId") Long userId);
}
