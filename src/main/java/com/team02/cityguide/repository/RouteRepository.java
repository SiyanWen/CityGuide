package com.team02.cityguide.repository;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserSpots;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RouteRepository extends ListCrudRepository<RouteEntity, Long> {

    List<RouteEntity> findByCreatorId(Long creatorId);

    @Modifying
    @Query("DELETE FROM routes WHERE name = :name")
    void deleteByName(String name);
}
