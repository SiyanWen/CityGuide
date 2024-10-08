package com.team02.cityguide.repository;

import com.team02.cityguide.entity.RouteEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RouteRepository extends ListCrudRepository<RouteEntity, Long> {

    RouteEntity findByRouteId(Long routeId);  // when can I simply write as findById?

    List<RouteEntity> findByName(String name); // <2>

    @Modifying
    @Query("DELETE FROM routes WHERE route_id = :routeId")   // route_id or id?
    void deleteByRouteId (Long routeId);

    @Modifying
    @Query("DELETE FROM routes WHERE name = :name")
    void deleteByName(String name);
}
