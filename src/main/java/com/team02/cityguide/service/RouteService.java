package com.team02.cityguide.service;

import com.team02.cityguide.DevRunner;
//import com.team02.cityguide.entity.CartSpotEntity;
import com.team02.cityguide.entity.CartSpots;
import com.team02.cityguide.entity.RouteEntity;
import com.team02.cityguide.entity.UnitRouteEntity;
//import com.team02.cityguide.entity.UserSpotEntity;
import com.team02.cityguide.external.RouteRequestBody;
import com.team02.cityguide.external.RouteResponseBody;
import com.team02.cityguide.external.WayPoint;
import com.team02.cityguide.model.Budget;
import com.team02.cityguide.model.RouteDto;
import com.team02.cityguide.model.SurveyBody;
import com.team02.cityguide.repository.CartSpotRepository;
import com.team02.cityguide.repository.RouteRepository;
import com.team02.cityguide.repository.UnitRouteRepository;
import com.team02.cityguide.repository.UserSpotRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team02.cityguide.external.GoogleApiService;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class RouteService {
    private final CartSpotRepository cartSpotRepository;
    private final RouteRepository routeRepository;
    private final UnitRouteRepository unitRouteRepository;
    private final UserSpotRepository userSpotRepository;
    private final GoogleApiService googleApiService;
    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);
    public RouteService(
            GoogleApiService googleApiService,
            CartSpotRepository cartSpotRepository,
            RouteRepository routeRepository,
            UnitRouteRepository unitRouteRepository,
            UserSpotRepository userSpotRepository
    ) {
        this.googleApiService = googleApiService;
        this.cartSpotRepository = cartSpotRepository;
        this.routeRepository = routeRepository;
        this.unitRouteRepository = unitRouteRepository;
        this.userSpotRepository = userSpotRepository;
    }

    @Transactional
    public RouteEntity updateRoute(RouteEntity route) {
        return routeRepository.save(route);  // Ensure this is within a transaction
    }

/*
 * SurveyBody structure
 *   daysToPlay
 *   List<StartEndPoints> (size = day + 1)
 *   List<traffic_mode> (size = day)
 *   SpotsPerDay
 *   Budget
 *       food
 *       transport
 *       ticket
 *       total

    public record SurveyBody(
            Integer daysToPlay,
            List<Long> startEndPoints,          // spot_ids
            List<String> trafficModes,
            Integer spotsPerDay,
            Budget budget
    ) {
    }

* */

    // // When survey is posted, check if typesJson has types of "lodging", "real_estate_agency", ...

    public List<RouteDto> planRoute(List<CartSpots> cartSpots, SurveyBody surveyBody) {
        logger.info("If SurveyBody is not null, SurveyBody: " + surveyBody.toString());
        // get 3 visiting plans for each day
        List<RouteDto> routePlans = new ArrayList<>();
        List<List<List<Long>>> plansOfSpotIdsByDay = spotsPermutation(cartSpots, surveyBody);
        // build unitRoutes (call Google API for each day's spots) and save to DB;
        for (int i = 0; i < plansOfSpotIdsByDay.size(); i++) { // for each plan, a route(Dto) is created
            if (plansOfSpotIdsByDay.get(i) == null) continue;
            List<UnitRouteEntity> unitRoutesCurPlan = new ArrayList<>();
            Double totalDistance = 0.0;
            Integer totalDuration = 0;
            List<String> trafficModes = new ArrayList<>();
            for (int j = 0; j < plansOfSpotIdsByDay.get(i).size(); j++) { // for each day
                if (plansOfSpotIdsByDay.get(i).get(j).size() <= 1) continue;
                for (int k = 0; k < plansOfSpotIdsByDay.get(i).get(j).size()-1; k++) { // for each starting spot
                    // build unitRoute
                    // call google computeRoutes() API
                    String startSpotGid = cartSpotRepository.findById(plansOfSpotIdsByDay.get(i).get(j).get(k)).get().getOriginalGid();
                    String endSpotGid = cartSpotRepository.findById(plansOfSpotIdsByDay.get(i).get(j).get(k+1)).get().getOriginalGid();
                    RouteRequestBody routeRequestBody = new RouteRequestBody(
                            new WayPoint(startSpotGid),          // UnitRouteStart
                            new WayPoint(endSpotGid),            // UnitRouteEnd
                            surveyBody.trafficModes(),   // trafficMode
                            false                               // computeAlternativeRoutes
                    );
                    RouteResponseBody routeResponseBody = googleApiService.computeRoutes(routeRequestBody);
                    logger.info("planned route (response body):{}",routeResponseBody);
                    String dur = routeResponseBody.routes().get(0).durationTime();
                    dur = dur.substring(0,dur.length() - 1);
                    Integer duration = Integer.parseInt(dur);
                    Double distance = (double) routeResponseBody.routes().get(0).distanceMeters();
                    UnitRouteEntity unitRoute = new UnitRouteEntity(
                            null,
                            null,
                            routeResponseBody.routes().get(0).polyline().encodedPolyline(),
                            j,
                            plansOfSpotIdsByDay.get(i).get(j).get(k),
                            plansOfSpotIdsByDay.get(i).get(j).get(k+1),
                            surveyBody.trafficModes(),
                            surveyBody.budget().total(),
                            distance,
                            duration
                    );
                    unitRoutesCurPlan.add(unitRoute);
                    totalDistance += distance;
                    totalDuration += duration;
                    if (!trafficModes.contains(surveyBody.trafficModes())) {
                        trafficModes.add(surveyBody.trafficModes());
                    }
                }
                unitRouteRepository.saveAll(unitRoutesCurPlan);
            }
            // save unitRoutesCurPlan
            routePlans.add(new RouteDto(
                    null, "routePlan_" + i,
                    "Description: This is route plan " + i + " based on your selections and preferences.",
                    totalDistance, trafficModes.toString(), surveyBody.budget().total(), totalDuration, unitRoutesCurPlan));
        }
        return routePlans;
    }

    // Each day spots seq: List<CartSpotEntityId>
    // All days spots seq: List<List<CartSpotEntityId>>
    // 3 plans of spots seq: List<List<List<CartSpotEntityId>>>; at least the first one is not null;
    private List<List<List<Long>>> spotsPermutation(List<CartSpots> cartSpots, SurveyBody surveyBody) {
        // sanity check
        int spotNumPerDay = Math.min(surveyBody.spotsPerDay(), 9);
        if (spotNumPerDay == 0) spotNumPerDay = 2;
        int maxSpotsPerDay = spotNumPerDay + 1;
        int minSpotsPerDay = spotNumPerDay - 1;
        if (spotNumPerDay == 1) minSpotsPerDay = 1;
        int totalSpots =  Math.min(cartSpots.size()-surveyBody.daysToPlay()-1, maxSpotsPerDay * surveyBody.daysToPlay());  // assume each day's start and end point is different and not in playPoints
        if (totalSpots < minSpotsPerDay * surveyBody.daysToPlay()) {
            spotNumPerDay = totalSpots / surveyBody.daysToPlay();
            minSpotsPerDay = spotNumPerDay - 1;
            maxSpotsPerDay = spotNumPerDay + 1;
        }

        List<List<List<Long>>> plansOfSpotIdsByDay = new ArrayList<>();
        Map<Long,Node> graph = buildGraph(cartSpots);
        PriorityQueue<Pair> spotPlans = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair.shortestTotalDistance));

        // Find How much solutions for day spots number pickup
        List<List<Integer>> spotNumPickUpSolutions = new ArrayList<>();     // this is guaranteed to be not null
        List<Integer> curSolutionBuilder = new ArrayList<>();
        spotPickUpDfs(surveyBody.daysToPlay(), totalSpots, minSpotsPerDay, maxSpotsPerDay, spotNumPickUpSolutions, curSolutionBuilder, 0);
        Collections.sort(spotNumPickUpSolutions, Comparator.comparingDouble(RouteService::calculateVariance));

        for (int i=0; i < Math.min(spotNumPickUpSolutions.size(), surveyBody.daysToPlay()); i++) {
            // choose the min variance solutions of spot picking number for each day
            // choose 3 SpotsSelections that have the shortestTotalDistance for each solution
            // input: graph, startEndPoints, spotNumPickSolution, spotPlans (how much spot other than start and end point each day?)
            List<Pair> threeSpotPlans = find3MinDistPlansOfSpotsSelection(graph, surveyBody.startEndPoints(), spotNumPickUpSolutions.get(i));
            spotPlans.addAll(threeSpotPlans);
        }

        for (int i=0; i<3; i++) {
            plansOfSpotIdsByDay.add(spotPlans.poll().spotPlan);
        }
        return plansOfSpotIdsByDay;
    }

    class Pair {
        Double shortestTotalDistance;
        List<List<Long>> spotPlan;
        Pair(Double shortestTotalDistance, List<List<Long>> spotPlan) {
            this.shortestTotalDistance = shortestTotalDistance;
            this.spotPlan = spotPlan;
        }
    }

    private List<Pair> find3MinDistPlansOfSpotsSelection(Map<Long, Node> graph, List<Long> startEndPoints, List<Integer> spotNumPickSolution) {
        List<Pair> plans = new ArrayList<>();
        PriorityQueue<Pair> results = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair.shortestTotalDistance));
        // DFS
        // init
        Set<Long> visited = new HashSet<>();
        int curDay = 0;
        int curDaySelectedCount = 0;
        Double distanceSoFar = 0.0;
        List<List<Long>> curPlan = new ArrayList<>();
        Node curStart = graph.get(startEndPoints.get(0));
        visited.add(startEndPoints.get(0));
        curPlan.add(new ArrayList<>());
        curPlan.get(0).add(startEndPoints.get(0));

        // DFS
        dfs(graph, startEndPoints, curStart, visited, curDay, curDaySelectedCount, spotNumPickSolution, results, curPlan, distanceSoFar);
        for (int i=0; i<3; i++) {
            plans.add(results.poll());
        }
        return plans;
    }

    private void dfs(Map<Long, Node> graph, List<Long> startEndPoints, Node curStart, Set<Long> visited, int curDay, int curDaySelectedCount, List<Integer> spotNumPickSolution, PriorityQueue<Pair> results, List<List<Long>> curPlan, Double distanceSoFar) {
        if (curDay == spotNumPickSolution.size()) {
            results.add(new Pair(distanceSoFar, curPlan));
            return;
        } else if (curDaySelectedCount == spotNumPickSolution.get(curDay)) {
            visited.add(startEndPoints.get(curDay + 1));
            distanceSoFar += calculateManhattanDistance(curStart.latitude, curStart.longitude, graph.get(startEndPoints.get(curDay + 1)).latitude, graph.get(startEndPoints.get(curDay + 1)).longitude);
            curPlan.get(curDay).add(startEndPoints.get(curDay + 1));
            curPlan.add(new ArrayList<>());
            curPlan.get(curDay + 1).add(startEndPoints.get(curDay+1));

            dfs(graph, startEndPoints, graph.get(startEndPoints.get(curDay + 1)), visited, curDay + 1, 0, spotNumPickSolution, results, curPlan, distanceSoFar);

            visited.remove(startEndPoints.get(curDay + 1));
            distanceSoFar -= calculateManhattanDistance(curStart.latitude, curStart.longitude, graph.get(startEndPoints.get(curDay + 1)).latitude, graph.get(startEndPoints.get(curDay + 1)).longitude);
            curPlan.get(curDay).remove(curPlan.get(curDay).size() - 1);
            curPlan.remove(curPlan.size() - 1);

            return;
        } else { // find next spot to visit
            for (Node neighbor : curStart.neighbors) {
                if (!visited.contains(neighbor.id)) {
                    visited.add(neighbor.id);
                    distanceSoFar += calculateManhattanDistance(curStart.latitude, curStart.longitude, neighbor.latitude, neighbor.longitude);
                    curPlan.get(curDay).add(neighbor.id);

                    dfs(graph, startEndPoints, neighbor, visited, curDay, curDaySelectedCount + 1, spotNumPickSolution, results, curPlan, distanceSoFar);

                    visited.remove(neighbor.id);
                    distanceSoFar -= calculateManhattanDistance(curStart.latitude, curStart.longitude, neighbor.latitude, neighbor.longitude);
                    curPlan.get(curDay).remove(curPlan.get(curDay).size() - 1);
                }
            }
        }
    }

    private void spotPickUpDfs(int daysToPlay, int totalSpots, int minSpotsPerDay, int maxSpotsPerDay, List<List<Integer>> spotNumPickUpSolutions, List<Integer> curSolutionBuilder, int curSum) {
        if (curSolutionBuilder.size() == daysToPlay) {
            if (curSum == totalSpots) {
                spotNumPickUpSolutions.add(curSolutionBuilder);
                return;
            }
            return;
        }

        for (int i = maxSpotsPerDay; i >= minSpotsPerDay; i--) {
            curSolutionBuilder.add(i);
            spotPickUpDfs(daysToPlay, totalSpots, minSpotsPerDay, maxSpotsPerDay, spotNumPickUpSolutions, curSolutionBuilder, curSum + i);
            curSolutionBuilder.remove(curSolutionBuilder.size() - 1);
        }
    }

    class Node {
        private final Long id;
        private final Double latitude;
        private final Double longitude;
        Map<Long, Integer> mapNeiDists = new HashMap<>();  // id -> distance
        List<Node> neighbors = new ArrayList<>();

        Node(Long id, Double latitude, Double longitude) {
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(id, node.id);
        }
    }

    private Map<Long, Node> buildGraph(List<CartSpots> cartSpots) {
        Map<Long, Node> nodesMap = new HashMap<>();
        for (CartSpots cartSpot : cartSpots) {
            nodesMap.put(cartSpot.getId(), new Node(cartSpot.getId(), cartSpot.getLatitude(), cartSpot.getLongitude()));
        }
        for (Map.Entry<Long, Node> nodeEntry : nodesMap.entrySet()) { // add neighbors
            for (CartSpots cartSpot : cartSpots) {
                if (nodeEntry.getValue().id == cartSpot.getId()) continue;
                if (!(nodeEntry.getValue().mapNeiDists == null) && nodeEntry.getValue().mapNeiDists.containsKey(cartSpot.getId())) continue;
                double distance = calculateManhattanDistance(nodeEntry.getValue().latitude, nodeEntry.getValue().longitude, cartSpot.getLatitude(), cartSpot.getLongitude());
                nodeEntry.getValue().mapNeiDists.put(cartSpot.getId(), (int) distance);
                nodeEntry.getValue().neighbors.add(new Node(cartSpot.getId(), cartSpot.getLatitude(), cartSpot.getLatitude()));
            }
        }
        for (Map.Entry<Long, Node> nodeEntry : nodesMap.entrySet()) { // sort neighbors
            nodeEntry.getValue().neighbors.sort(Comparator.comparingDouble(o -> o.mapNeiDists.get(nodeEntry.getValue().id)));
        }
        return nodesMap;
    }

    private static final double EARTH_RADIUS_KM = 6371.0;

    private double calculateManhattanDistance(double lat1, double lon1, double lat2, double lon2) {

        // Convert degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate differences
        double deltaLat = Math.abs(lat2Rad - lat1Rad);
        double deltaLon = Math.abs(lon2Rad - lon1Rad);

        // Calculate north-south distance
        double nsDistance = deltaLat * EARTH_RADIUS_KM;

        // Calculate east-west distance
        double averageLat = (lat1Rad + lat2Rad) / 2;
        double ewDistance = deltaLon * Math.cos(averageLat) * EARTH_RADIUS_KM;

        // Sum of north-south and east-west distances - in km
        return nsDistance + ewDistance;
    }

    public static double calculateVariance(List<Integer> numbers) {
        double sum = 0.0;
        double mean;
        double temp = 0.0;
        int n = numbers.size();
        for (int number : numbers) {
            sum += number;
        }
        mean = sum / n;
        for (int number : numbers) {
            temp += (mean - number) * (mean - number);
        }
        return temp / n;
    }
}
