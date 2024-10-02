package com.team02.cityguide.service;

import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // TODO
}
