package com.team02.cityguide.service;

import com.team02.cityguide.entity.*;
import com.team02.cityguide.repository.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;
    private final RouteRepository routeRepository;
    private final RouteLikeRepository routeLikeRepository;
    private final SpotLikeRepository spotLikeRepository;
    private final CartSpotRepository cartSpotRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager, RouteRepository routeRepository, RouteLikeRepository routeLikeRepository, SpotLikeRepository spotLikeRepository, CartSpotRepository cartSpotRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
        this.routeRepository = routeRepository;
        this.routeLikeRepository = routeLikeRepository;
        this.spotLikeRepository = spotLikeRepository;
        this.cartSpotRepository = cartSpotRepository;
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Transactional
    public void signup(String email, String password, String userName, String cityId, String profileURL) {
        System.out.printf("receive fro signup");
        email = email.toLowerCase();
        UserDetails user = User.builder()
                .username(email)
                        .password(passwordEncoder.encode(password))
                                .roles("USER")
                                        .build();
        userDetailsManager.createUser(user);
        userRepository.updateUserNameByEmail(email, userName);
        userRepository.updateCityIDByEmail(cityId, email);
        userRepository.updateProfileURLByEmail(profileURL, email);
        UserEntity savedUser = userRepository.findByEmail(email);
//      TODO: update other table and change the save
        //      FIXME: what is spotID here!!!!!

    }
}
