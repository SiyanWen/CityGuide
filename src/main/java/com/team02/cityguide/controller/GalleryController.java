package com.team02.cityguide.controller;

import com.team02.cityguide.model.AddRouteBody;
import com.team02.cityguide.model.AddSpotBody;
import com.team02.cityguide.model.RouteGalleryDto;
import com.team02.cityguide.model.SpotGalleryDto;
import com.team02.cityguide.service.CartService;
import com.team02.cityguide.service.GalleryService;
import com.team02.cityguide.service.UserService;
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
    public SpotGalleryDto getSpotGallery() {
        // getSpotGalleryByUserId
        return null;
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

    @GetMapping("/routeGallery/{galleryId}")
    public RouteGalleryDto getRouteGalleryByUserId(@PathVariable("galleryId") Long galleryId) {
        // check if the route is created by the user and
        return null;
    }

    @PostMapping("/spotGallery")
    public void addSpotToGallery(@RequestBody AddSpotBody addSpotBody) {
        //
    }

    @PostMapping("/routeGallery/{galleryId}")  // 1 = my trip plan; 2 = route collected from others
    public void addRouteToGallery(@PathVariable("galleryId") Long galleryId, @RequestBody AddRouteBody addRouteBody) {
        //
    }

    @DeleteMapping("/spotGallery/{spotId}")
    public void removeSpotFromGallery(@PathVariable("spotId") Long spotId) {

    }

    @DeleteMapping("/routeGallery/{galleryId}/{routeId}")
    public void removeRouteFromGallery(@PathVariable("galleryId") Long galleryId, @PathVariable("routeId") Long routeId) {

    }
}
