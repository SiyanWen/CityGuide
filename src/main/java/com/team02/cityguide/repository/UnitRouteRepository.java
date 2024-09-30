package com.team02.cityguide.repository;

import com.team02.cityguide.entity.UnitRouteEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UnitRouteRepository extends ListCrudRepository<UnitRouteEntity, Long> {

    List<UnitRouteEntity> findByRouteId (Long routeId);

    List<UnitRouteEntity> findByGooglePolylineId (Long googlePolylineId);

    @Modifying
    @Query("DELETE FROM unit_routes WHERE route_id = :routeId")
    void deleteByRouteId (Long routeId);
}
