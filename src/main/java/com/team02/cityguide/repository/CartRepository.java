package com.team02.cityguide.repository;

import com.team02.cityguide.entity.CartEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface CartRepository extends ListCrudRepository<CartEntity, Long> {
    CartEntity findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM carts WHERE user_id = :userId")
    void deleteByUserId(Long userId);
}
