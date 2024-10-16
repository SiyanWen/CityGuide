package com.team02.cityguide.service;

import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.model.UserDto;
import com.team02.cityguide.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.RouteLikeEntity;
import com.team02.cityguide.entity.SpotLikeEntity;
import com.team02.cityguide.entity.UnitRouteEntity;
import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.entity.UserSpotEntity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;

    public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        UserDetailsManager userDetailsManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void signUp(String email, String password, String userName, String cityId) {
        email = email.toLowerCase();
        UserDetails user = User.builder()
                .username(email)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        userRepository.updateUserNameByEmail(email, userName);
        userRepository.updateCityIdByEmail(email, cityId);

        UserEntity savedUser = userRepository.findByEmail(email);
    }

    public UserDto getUserInfo(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            return new UserDto(
                user.userName(),
                user.email(),
                user.password(),
                user.cityId()
            );
        }
        return null;
    }
}
