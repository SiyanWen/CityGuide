package com.team02.cityguide.service;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.RouteGalleryEntity;
import com.team02.cityguide.entity.SpotGalleryEntity;
import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    private final RouteRepository routeRepository;
    private final RouteGalleryRepository RouteGalleryRepository;
    private final SpotGalleryRepository spotGalleryRepository;

    public GalleryService(RouteRepository routeRepository, RouteGalleryRepository routeGalleryRepository, SpotGalleryRepository spotGalleryRepository) {
        this.routeRepository = routeRepository;
        this.RouteGalleryRepository = routeGalleryRepository;
        this.spotGalleryRepository = spotGalleryRepository;
    }

    // TODO
    public void saveSpotToGallery(UserSpotEntity userSpotEntity) {

    }

    // TODO
    public void saveRouteToGallery(RouteEntity routeEntity) {

    }

    // TODO
    public void removeSpotFromGallery(UserSpotEntity userSpotEntity, SpotGalleryEntity spotGalleryEntity) {

    }

    // TODO
    public void removeRouteFromGallery(RouteEntity routeEntity, RouteGalleryEntity routeGalleryEntity) {

    }

    // TODO
    public SpotGalleryEntity getSpotGalleryDto() {
        return null;
    }

    // TODO
    public RouteGalleryEntity getRouteGalleryDto() {
        return null;
    }

    // TODO
    public RouteGalleryEntity getMyTripPlanDto() {
        return null;
    }
}
