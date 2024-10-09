package com.team02.cityguide.controller;

import com.team02.cityguide.model.AddRouteBody;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.CartDto;
import com.team02.cityguide.service.GalleryService;
import com.team02.cityguide.service.CartService;
import com.team02.cityguide.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final GalleryService galleryService;

    public CartController(CartService cartService, UserService userService, GalleryService galleryService) {
        this.cartService = cartService;
        this.userService = userService;
        this.galleryService = galleryService;
    }

    @GetMapping("/cart")
    public CartDto getCart() {
        return null;
    }

    @PostMapping("/cart/spot")
    public void addSpotToCart(@RequestBody AddSpotBody addSpotBody) {

    }

    @PostMapping("/cart/route")
    public void addRouteToCart(@RequestBody AddRouteBody addRouteBody) {

    }

    @DeleteMapping("/cart/spot/{spotId}")    // why not @DeleteMapping("/cart") + (@RequestBody AddSpotBody addSpotBody) ?
    public void removeSpotFromCart(@PathVariable("spotId") Long spotId) {

    }
}
