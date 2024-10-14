package com.team02.cityguide.controller;

import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.model.AddRouteToCartBody;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.RouteGalleryDto;
import com.team02.cityguide.model.SpotGalleryDto;
import com.team02.cityguide.service.CartService;
import com.team02.cityguide.service.GalleryService;
import com.team02.cityguide.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class GalleryController {
    private final GalleryService galleryService;
    private final UserService userService;
    private final CartService cartService;

    public GalleryController(GalleryService galleryService, UserService userService, CartService cartService) {
        this.galleryService = galleryService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/spotGallery")
    public SpotGalleryDto getSpotGallery(@AuthenticationPrincipal User user) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        return galleryService.getSpotGalleryDto(userEntity.id());
    }

    @GetMapping("/routeGallery/{galleryId}")
    public RouteGalleryDto getRouteGalleryByUserId(
            @AuthenticationPrincipal User user,
            @PathVariable("galleryId") Long galleryId
    ) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        return galleryService.getRouteGalleryDto(userEntity.id(), galleryId);
    }

    @PostMapping("/spotGallery")
    public void addSpotToGallery(@AuthenticationPrincipal User user, @RequestBody AddSpotBody addSpotBody) {
        // In gallery service, there would be check if the spot already existed; not checked here
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        galleryService.saveSpotToGallery(userEntity.id(), addSpotBody);
    }

    @PostMapping("/routeGallery/2")  // 1 = my trip plan; 2 = route collected from others
    public void addRouteToGallery(
            @AuthenticationPrincipal User user,
//            @PathVariable("galleryId") Long galleryId,
            @RequestBody AddRouteToCartBody addRouteBody
    ) {
        // RouteEntity route = RouteService.getRouteById(addRouteToCartBody.routeId());
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        galleryService.saveRouteToGallery(userEntity.id(), 2L, addRouteBody.routeId());
    }

    //saveRouteToMyTripPlan
    @PostMapping("/routeGallery/1")  // 1 = my trip plan; 2 = route collected from others
    public void addRouteToMyTripPlan(
            @AuthenticationPrincipal User user,
//            @PathVariable("galleryId") Long galleryId,
            @RequestBody RouteEntity routeEntity
    ) {
        // RouteEntity route = RouteService.getRouteById(addRouteToCartBody.routeId());
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        galleryService.saveRouteToMyTripPlan(userEntity.id(), 2L, routeEntity);
        cartService.clearCart(userEntity.id());
    }

    @DeleteMapping("/spotGallery/{spotId}")
    public void removeSpotFromGallery(@AuthenticationPrincipal User user, @PathVariable("spotId") Long spotId) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        galleryService.removeSpotFromGallery(userEntity.id(), spotId);
    }

    @DeleteMapping("/routeGallery/{galleryId}/{routeId}")
    public void removeRouteFromGallery(
            @AuthenticationPrincipal User user,
            @PathVariable("galleryId") Long galleryId,
            @PathVariable("routeId") Long routeId
    ) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        galleryService.removeRouteFromGallery(userEntity.id(), galleryId, routeId);
    }
}


    /*
    * @RestController
        public class UserController {

            @GetMapping("/users/{id}")
            public User getUser(@PathVariable Long id) {
                // 处理获取特定用户的请求
            }

            @GetMapping("/users")
            public List<User> getUsers(@RequestParam(required = false) String name) {
                // 处理获取特定用户名称的用户列表的请求
            }
        }
        https://example.com/filter?showHidden=yes (true)
        https://example.com/filter?showHidden=no (false)
        @PostMapping("/api/search")
        public List<Product> searchProducts(
            @RequestParam(value = "showHidden", defaultValue = "false") boolean showHidden
        ) {
            // ... your search logic
        }
        https://example.com/api/search?showHidden=true
        https://example.com/api/search?showHidden=false
    * */