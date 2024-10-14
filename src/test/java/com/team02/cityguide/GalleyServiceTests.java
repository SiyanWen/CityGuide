package com.team02.cityguide;

import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.repository.RouteLikeRepository;
import com.team02.cityguide.repository.RouteRepository;
import com.team02.cityguide.repository.SpotLikeRepository;
import com.team02.cityguide.service.GalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GalleyServiceTests {

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private RouteLikeRepository routeLikeRepository;

    @Mock
    private SpotLikeRepository spotLikeRepository;

    private GalleryService galleryService;

    @BeforeEach
    public void setUp() {
        galleryService = new GalleryService(routeRepository, routeLikeRepository, spotLikeRepository);
    }

    @Test
    void saveSpotToGalleyTest_noUserSpotEntity() {
        UserSpotEntity userSpotEntity = new UserSpotEntity(1L, 1L, 1L, 1L, "", "", "", "", 40.7128, -74.006, 2, 0.0, 4.8, 1200, "","", "");

    }
}
