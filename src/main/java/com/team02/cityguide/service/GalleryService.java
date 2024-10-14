package com.team02.cityguide.service;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.RouteLikeEntity;
import com.team02.cityguide.entity.SpotLikeEntity;
import com.team02.cityguide.entity.UserSpotEntity;
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
    public void saveSpotToGallery(UserSpotEntity userSpotEntity) {

    }

    // TODO
    public void saveRouteToGallery(RouteEntity routeEntity) {

    }

    // TODO
    public void removeSpotFromGallery(UserSpotEntity userSpotEntity, SpotLikeEntity spotGalleryEntity) {

    }

    // TODO
    public void removeRouteFromGallery(RouteEntity routeEntity, RouteLikeEntity routeGalleryEntity) {

    }

    // TODO
    public SpotLikeEntity getSpotGalleryDto() {
        return null;
    }

    // TODO
    public RouteLikeEntity getRouteGalleryDto() {
        return null;
    }

    // TODO
    public RouteLikeEntity getMyTripPlanDto() {
        return null;
    }
}
