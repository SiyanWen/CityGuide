package com.team02.cityguide.repository;

import com.team02.cityguide.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {
    // List<UserEntity> findByFirstName(String firstName);

    // List<UserEntity> findByLastName(String lastName);
    
    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    @Modifying
    @Query("UPDATE users SET user_name = :userName WHERE email = :email")
    void updateUserNameByEmail(String email, String userName);
}
