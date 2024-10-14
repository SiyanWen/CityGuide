package com.team02.cityguide;

import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.repository.UserRepository;
import com.team02.cityguide.repository.RouteRepository;
import com.team02.cityguide.repository.UserSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.RouteMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Rollback DB changes after each test
@ExtendWith(MockitoExtension.class)
public class CityGuideApplicationTests {

    @Autowired
    @Mock
    private UserRepository userRepository;

    @Autowired
    @Mock
    private RouteRepository routeRepository;

    @Autowired
    @Mock
    private UserSpotRepository userSpotRepository;

    private UserEntity testUser;
    private RouteEntity testRoute;
    private UserSpotEntity testSpot;

    @BeforeEach
    void setUp() {
        // Initialize entities for testing
        testUser = new UserEntity(4L, "test.user@example.com", "password123", "Test User", "https://example.com/images/testuser.jpg", "CITY001", true);

        testRoute = new RouteEntity(4L, 4L, "Test Route", "A test route", 5.0, "Waling", 20.0, 2);

        testSpot = new UserSpotEntity(4L, 4L, 4L, 4L, "GID123", "Test Spot", "Test Address1", "test description", 40.7, -74.0, 2, 15.0, 4.5, 3, "openhours", "coverImgUrl", "reviews");
    }

    @Test
    public void testCreateAndReadUser() {
        // Create
        UserEntity savedUser = userRepository.save(testUser);
        assertNotNull(testUser.id());

        // Read
        Optional<UserEntity> foundUser = userRepository.findById(savedUser.id());
        assertTrue(foundUser.isPresent());
        assertEquals("test.user@example.com", foundUser.get().email());
    }

    @Test
    public void testUpdateUser() {
        // Create
        UserEntity savedUser = userRepository.save(testUser);

        // Update
        userRepository.updateUserNameByEmail("test.user@example.com", "updated username");

        assertEquals("updated username", savedUser.userName());
    }


    @Test
    public void testDeleteUser() {
        // Create
        UserEntity savedUser = userRepository.save(testUser);

        // Delete
        userRepository.delete(savedUser);
        Optional<UserEntity> foundUser = userRepository.findById(savedUser.id());

        assertFalse(foundUser.isPresent());
    }

    @Test
    public void testCreateAndReadRoute() {

        // Create route
        RouteEntity savedRoute = routeRepository.save(testRoute);
        assertNotNull(savedRoute.id());

        // Read route
        Optional<RouteEntity> foundRoute = routeRepository.findById(savedRoute.id());
        assertTrue(foundRoute.isPresent());
        assertEquals("Test Route", foundRoute.get().name());
    }


    @Test
    public void testDeleteRoute() {

        RouteEntity savedRoute = routeRepository.save(testRoute);

        // Delete route
        routeRepository.deleteByName(savedRoute.name());
        Optional<RouteEntity> foundRoute = routeRepository.findById(savedRoute.id());

        assertFalse(foundRoute.isPresent());
    }

    @Test
    public void testCreateAndReadUserSpot() {
        // Save user spot
        UserSpotEntity savedSpot = userSpotRepository.save(testSpot);
        assertNotNull(savedSpot.id());

        // Read user spot
        Optional<UserSpotEntity> foundSpot = userSpotRepository.findById(savedSpot.id());
        assertTrue(foundSpot.isPresent());
        assertEquals("Test Spot", foundSpot.get().name());
    }


    @Test
    public void testDeleteUserSpot() {
        UserSpotEntity savedSpot = userSpotRepository.save(testSpot);

        // Delete spot
        userSpotRepository.deleteById(savedSpot.id());
        Optional<UserSpotEntity> foundSpot = userSpotRepository.findById(savedSpot.id());

        assertFalse(foundSpot.isPresent());
    }

    @Test
    public void testDeleteUserSpotByUserID() {
        UserSpotEntity savedSpot = userSpotRepository.save(testSpot);
        UserSpotEntity savedUser = userSpotRepository.findByOriginalGid(savedSpot.originalGid());
        assertEquals(savedUser, savedSpot);
        // Delete spot
//        TODO: originalGid is it String or Long (Long is from UserSpotRepository)
        userSpotRepository.deleteByUserId(savedSpot.originalGid());
        Optional<UserSpotEntity> foundSpot = userSpotRepository.findById(savedSpot.id());

        assertFalse(foundSpot.isPresent());
    }

    @Test
    public void testfindbyorigialGid() {
        UserSpotEntity savedSpot = userSpotRepository.save(testSpot);

        // Delete spot
//        TODO: originalGid is it String or Long (Long is from UserSpotRepository)
        userSpotRepository.deleteByUserId(savedSpot.originalGid());
        Optional<UserSpotEntity> foundSpot = userSpotRepository.findById(savedSpot.id());

        assertFalse(foundSpot.isPresent());
    }
}
