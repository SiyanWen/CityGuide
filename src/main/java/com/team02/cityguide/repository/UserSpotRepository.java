package com.team02.cityguide.repository;

//import com.team02.cityguide.entity.UserEntity;
//import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.entity.UserSpots;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserSpotRepository extends ListCrudRepository<UserSpots, Long> {

    UserSpots findByOriginalGid(String originalGid);

    List<UserSpots> findByRouteId(Long routeId);


    @Modifying
    @Query("DELETE FROM user_spots WHERE id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("DELETE FROM user_spots WHERE original_gid = :originalGid")
    void deleteByOriginalGid(String originalGid);
}
