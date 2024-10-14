package com.team02.cityguide.service;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.RouteLikeEntity;
import com.team02.cityguide.entity.SpotLikeEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.RouteGalleryDto;
import com.team02.cityguide.model.SpotGalleryDto;
import com.team02.cityguide.repository.*;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

    private final RouteRepository routeRepository;
    private final RouteLikeRepository RouteLikeRepository;
    private final SpotLikeRepository spotLikeRepository;

    public GalleryService(RouteRepository routeRepository, RouteLikeRepository routeLikeRepository, SpotLikeRepository spotLikeRepository) {
        this.routeRepository = routeRepository;
        this.RouteLikeRepository = routeLikeRepository;
        this.spotLikeRepository = spotLikeRepository;
    }

    // TODO
    public void saveSpotToGallery(Long userId, AddSpotBody addSpotBody) {

    }

    // TODO
    public void saveRouteToGallery(Long userId, Long galleryId, Long routeId) {

    }

    // TODO
    public void saveRouteToMyTripPlan(Long userId, Long galleryId, RouteEntity routeEntity) {

    }

    // TODO
    public void removeSpotFromGallery(Long userId, Long spotId) {

    }

    // TODO
    public void removeRouteFromGallery(Long userId, Long galleryId, Long routeId) {

    }

    // TODO
    public SpotGalleryDto getSpotGalleryDto(Long userId) {
        return null;
    }

    // TODO
    public RouteGalleryDto getRouteGalleryDto(Long userId, Long galleryId) {
        return null;
    }
}
