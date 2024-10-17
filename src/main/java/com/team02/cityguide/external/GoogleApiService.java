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

    public RouteResponseBody computeRoutes(RouteRequestBody routeRequestBody) {

        String url = "https://routes.googleapis.com/directions/v2:computeRoutes";

        // Create a Java object representing the request body
        RouteRequestBody routeRequestBodyFormatted = new RouteRequestBody(
                routeRequestBody.origin(),      // Nordheim Court
                routeRequestBody.destination(),      // Maple hall
                routeRequestBody.travelMode() == null ? "DRIVE" : routeRequestBody.travelMode(),     // trafficMode
                routeRequestBody.computeAlternativeRoutes() == null ? false : routeRequestBody.computeAlternativeRoutes()
        );

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth("AIzaSyDpTfhoLV3P_o68qc-i_yHf_IOc8LIrIW8");
        headers.add("X-Goog-Api-Key", "AIzaSyDpTfhoLV3P_o68qc-i_yHf_IOc8LIrIW8");
        headers.add("X-Goog-FieldMask", "routes.duration,routes.distanceMeters,routes.polyline.encodedPolyline,routes.description");

        // Create HttpEntity with the request body and headers
        HttpEntity<RouteRequestBody> entity = new HttpEntity<>(routeRequestBody, headers);

        // Send POST request
        ResponseEntity<RouteResponseBody> response = restTemplate.postForEntity(url, entity, RouteResponseBody.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();  // Returns the RoutesResponse object
        } else {
            throw new RuntimeException("Failed to fetch routes when calling google computeRoutes() api");
        }
    }
}
