package com.team02.cityguide.external;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "google-api")
//public interface GoogleApiClient {

//    @GetMapping("/computeRoutes")
//    String getRoute(@RequestParam String startPlaceId, @RequestParam String endPlaceId, @RequestParam String mode);

//    private final WebClient.Builder webClientBuilder;
//
//    @Autowired
//    public GoogleMapsService(WebClient.Builder webClientBuilder) {
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    public String computeRoutes() {
//        String url = "https://routes.googleapis.com/directions/v2:computeRoutes";
//
//        // Create a Java object representing the request body
//        RouteRequest routeRequest = new RouteRequest(
//                "ChIJf5Esy4cUkFQRKK6M06RW2YI",      // Nordheim Court
//                "ChIJV4bOpvcVkFQRJA-LICOZe6Y",      // Maple hall
//                "DRIVE"                             // travelMode
//        );
//
//        // Send POST request
//        String response = webClientBuilder.build()
//                .post()
//                .uri(url)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .header(HttpHeaders.AUTHORIZATION, "Bearer YOUR_ACCESS_TOKEN")
//                .bodyValue(routeRequest)  // Pass the Java object directly
//                .retrieve()
//                .bodyToMono(String.class)
//                .block(); // Blocking for simplicity, use without block() for async
//
//        return response;
//    }
//}
