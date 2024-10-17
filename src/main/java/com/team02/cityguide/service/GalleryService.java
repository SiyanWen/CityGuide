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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {

    private final RouteRepository routeRepository;
    private final RouteLikeRepository routeLikeRepository;
    private final SpotLikeRepository spotLikeRepository;
    private final UserSpotRepository userSpotRepository;

    public GalleryService(RouteRepository routeRepository, RouteLikeRepository routeLikeRepository, SpotLikeRepository spotLikeRepository,UserSpotRepository userSpotRepository) {
        this.routeRepository = routeRepository;
        this.routeLikeRepository = routeLikeRepository;
        this.spotLikeRepository = spotLikeRepository;
        this.userSpotRepository = userSpotRepository;
    }

    // TODO
    public void saveSpotToGallery(Long userId, AddSpotBody addSpotBody) {
        UserSpotEntity userSpot=userSpotRepository.findByOriginalGid(addSpotBody.originalGid());
        if(userSpot==null) {
            userSpot= new UserSpotEntity(null,addSpotBody.routeId(),addSpotBody.originalGid(),addSpotBody.name(),addSpotBody.address(),addSpotBody.description(),addSpotBody.latitude(),addSpotBody.longitude(),addSpotBody.durationTime(),addSpotBody.cost(),addSpotBody.rating(),addSpotBody.ratingCount(),addSpotBody.openingHours(),addSpotBody.types(),addSpotBody.coverImgUrl(),addSpotBody.reviews());
            userSpotRepository.save(userSpot);
            SpotLikeEntity spotLike=new SpotLikeEntity(null,userId,userSpot.id());
            spotLikeRepository.save(spotLike);
        }else{
            SpotLikeEntity spotLike = spotLikeRepository.findByUserIdAndSpotId(userId, userSpot.id());
            if (spotLike == null) {
                spotLike=new SpotLikeEntity(null,userId,userSpot.id());
                spotLikeRepository.save(spotLike);
            }
        }
    }

    // TODO
    public void saveRouteToGallery(Long userId, Long galleryId, Long routeId) {

        Optional<RouteEntity> route = routeRepository.findById(routeId);
        if(route.isPresent()){
            RouteEntity routeEntity=route.get();
            RouteLikeEntity routeLikeEntity = new RouteLikeEntity(null,userId,routeEntity.id());
            routeLikeRepository.save(routeLikeEntity);
        }
    }

    // TODO
    public void saveRouteToMyTripPlan(Long userId, Long galleryId, RouteEntity routeEntity) {
        RouteLikeEntity routeLikeEntity = new RouteLikeEntity(null,userId,routeEntity.id());
        routeLikeRepository.save(routeLikeEntity);
    }

    // TODO
    public void removeSpotFromGallery(Long userId, Long spotId) {
        spotLikeRepository.deleteByUserIdAndSpotId(userId, spotId);
    }

    // TODO
    public void removeRouteFromGallery(Long userId, Long galleryId, Long routeId) {
        routeLikeRepository.deleteByUserIdAndRouteId(userId,routeId);
    }

    // TODO
    public SpotGalleryDto getSpotGalleryDto(Long userId) {
        List<SpotLikeEntity> likedUserAndSpotPairs = spotLikeRepository.findByUserId(userId);
        List<UserSpotEntity> spots = new ArrayList<>();
        if(likedUserAndSpotPairs.size()>0) {
            for(SpotLikeEntity spotLikeEntity : likedUserAndSpotPairs){
                UserSpotEntity likedSpot = userSpotRepository.findById(spotLikeEntity.userId()).orElse(null);
                spots.add(likedSpot);
            }
        }
        return new SpotGalleryDto(userId,spots);
    }

    // TODO
    public RouteGalleryDto getRouteGalleryDto(Long userId, Long galleryId) {
        List<RouteEntity> result = new ArrayList<>();
        if(galleryId==1L){
            result = routeRepository.findByCreatorId(userId);
        } else if (galleryId==2L) {
            List<RouteLikeEntity> likedUserAndRoutePairs = routeLikeRepository.findByUserId(userId);
            if(likedUserAndRoutePairs.size()>0) {
                for(RouteLikeEntity routeLikeEntity : likedUserAndRoutePairs){
                    RouteEntity routeEntity = routeRepository.findById(routeLikeEntity.routeId()).orElse(null);
                    result.add(routeEntity);
                }
            }
        }
        return new RouteGalleryDto(userId,result);
    }
}
