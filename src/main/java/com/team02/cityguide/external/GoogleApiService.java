package com.team02.cityguide.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public GoogleApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String computeRoutes() {

        String url = "https://routes.googleapis.com/directions/v2:computeRoutes";

        // Create a Java object representing the request body
        RouteRequest routeRequest = new RouteRequest(
                "ChIJf5Esy4cUkFQRKK6M06RW2YI",      // Nordheim Court
                "ChIJV4bOpvcVkFQRJA-LICOZe6Y",      // Maple hall
                "DRIVE"                             // travelMode
        );

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("AIzaSyDpTfhoLV3P_o68qc-i_yHf_IOc8LIrIW8");

        // Create HttpEntity with the request body and headers
        HttpEntity<RouteRequest> entity = new HttpEntity<>(routeRequest, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response.getBody();

    }
}
