package com.team02.cityguide.controller;

import com.team02.cityguide.entity.UserEntity;
import com.team02.cityguide.model.AddRouteToCartBody;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.CartDto;
import com.team02.cityguide.service.GalleryService;
import com.team02.cityguide.service.CartService;
import com.team02.cityguide.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public CartDto getCart(@AuthenticationPrincipal User user) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        return cartService.getCart(userEntity.id());
    }

    @PostMapping("/cart/spot")
    public void addSpotToCart(@AuthenticationPrincipal User user, @RequestBody AddSpotBody addSpotBody) {
        // when adding spot to cart, it may not exist in the DB, so pass in the whole body;
        //          slow, can be optimised by using if-else check here, and add a method in the cart service
        // in cart service, there would be check if the spot already existed; not checked here
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        cartService.addSpotToCart(userEntity.id(), addSpotBody);
    }

    @PostMapping("/cart/route")
    public void addRouteToCart(@AuthenticationPrincipal User user, @RequestBody AddRouteToCartBody addRouteToCartBody) {
        // when adding route(spots) to cart, it must have already existed in the DB, so just pass in route_id
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        cartService.addRouteToCart(userEntity.id(), addRouteToCartBody.routeId());
    }

    @DeleteMapping("/cart/spot/{spotId}")    // why not @DeleteMapping("/cart") + (@RequestBody AddSpotBody addSpotBody) ?
    public void removeSpotFromCart(@AuthenticationPrincipal User user, @PathVariable("spotId") Long spotId) {
        UserEntity userEntity = userService.findByEmail(user.getUsername());
        cartService.removeSpotFromCart(spotId, userEntity.id());
    }
}
