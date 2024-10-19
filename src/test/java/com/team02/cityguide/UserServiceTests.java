package com.team02.cityguide;

import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.repository.*;
import com.team02.cityguide.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    UserDetailsManager userDetailsManager;

    @Mock
    RouteRepository routeRepository;

    @Mock
    RouteLikeRepository routeLikeRepository;

    @Mock
    SpotLikeRepository spotLikeRepository;

    @Mock
    CartSpotRepository cartSpotRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder, userDetailsManager);
    }

    @Test
    void findByEmailTest_EmailExist(){
        String email = "john.doe@example.com";
        UserEntity userEntity = new UserEntity(1L, email, "hashed_password_123", "John Doe", "https://example.com/images/john.jpg", "C001", true);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(userEntity);

        UserEntity userEntity1 = userService.findByEmail(email);
        Assertions.assertEquals(userEntity1, userEntity);
    }

    @Test
    void findByEmailTest_EmailNotExist(){
        String email = "notexist@example.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(null);
        UserEntity userEntity1 = userService.findByEmail(email);
        Assertions.assertNull(userEntity1);
    }

}
