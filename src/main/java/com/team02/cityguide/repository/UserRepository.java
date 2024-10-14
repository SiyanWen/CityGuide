package com.team02.cityguide.repository;

import com.team02.cityguide.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

//    UserEntity findByUsername(String userName);

    void deleteByEmail(String email);

    @Modifying
    @Query("UPDATE users SET user_name = :userName WHERE email = :email")
    void updateUserNameByEmail(String email, String userName);

    @Modifying
    @Query("UPDATE users SET email = :email WHERE user_name = :userName")
    void updateEmailByUsername(String email, String userName);

    @Modifying
    @Query("UPDATE users SET profile_pic_url = :profileURL WHERE email = :email")
    void updateProfileURLByEmail(String profileURL, String email);

    @Modifying
    @Query("UPDATE users SET city_id = :cityId WHERE email = :email")
    void updateCityIDByEmail(String cityId, String email);

//    @Modifying
//    @Query("UPDATE users SET enabled = :enabled WHERE user_name = :userName")
//    void updateStatusByUsername(Boolean enabled, String userName);
}
