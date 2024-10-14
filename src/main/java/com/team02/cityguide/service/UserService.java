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
//                                .disabled(false)
                                        .build();
        userDetailsManager.createUser(user);
        userRepository.updateUserNameByEmail(email, userName);
        userRepository.updateCityIDByEmail(cityId, email);
        userRepository.updateProfileURLByEmail(profileURL, email);
        UserEntity savedUser = userRepository.findByEmail(email);
//      TODO: update other table and change the save
        //      FIXME: what is spotID here!!!!!

//        RouteEntity routeEntity = new RouteEntity(4L, savedUser.id(), "routename", "routeDes", 20.0, "car", 2.0, 1);
//        routeRepository.save(routeEntity);
//        RouteLikeEntity routeLikeEntity = new RouteLikeEntity(4L, savedUser.id(), routeEntity.id());
//        routeLikeRepository.save(routeLikeEntity);
//        SpotLikeEntity spotLikeEntity = new SpotLikeEntity(4L, savedUser.id(), 2L);
//        spotLikeRepository.save(spotLikeEntity);
//        CartSpotEntity cartSpotEntity = new CartSpotEntity(4L, "GID", savedUser.id(), "name", "address", 5.0, 200, 55.0, 2, "hours", 24.0, 27.0, "IMG");
//        cartSpotRepository.save(cartSpotEntity);
    }

//    @Transactional
//    public Boolean login(String email, String password) {
//        UserEntity user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        //TODO: change to encoded security
////        if (user.password() != passwordEncoder.encode(password)) {
////            throw new BadCredentialsException("Bad credentials");
////        }
//
//        return password.equals(user.password());
//    }
}
