package com.team02.cityguide.repository;

import com.team02.cityguide.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {
    List<UserEntity> findByUserName(String userName);

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    @Modifying
    @Query("UPDATE users SET user_name = :userName WHERE email = :email")
    void updateUserNameByEmail(String email, String userName);

    @Modifying
    @Query("UPDATE users SET city_id = :city_id WHERE email = :email")
    void updateCityIDByEmail(String email, String city_id);


    @Modifying
    @Query("UPDATE users SET profile_pic_url = :profile_pic_url WHERE email = :email")
    void updateProfileURLByEmail(String profile_pic_url, String email);


}
