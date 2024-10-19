package com.team02.cityguide.service;

import com.team02.cityguide.entity.*;
import com.team02.cityguide.model.UserDto;
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

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Transactional
    public void signup(String email, String password, String userName, String cityId) {
        System.out.print("receive fro signup");
        email = email.toLowerCase();
        UserDetails user = User.builder()
                .username(email)
                        .password(passwordEncoder.encode(password))
                                .roles("USER")
                                        .build();
        userDetailsManager.createUser(user);
        userRepository.updateUserNameByEmail(email, userName);
        userRepository.updateCityIDByEmail(cityId, email);
//        UserEntity savedUser = userRepository.findByEmail(email);
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
