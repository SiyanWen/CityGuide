package com.team02.cityguide.model;

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
 * */

import com.team02.cityguide.entity.UserSpotEntity;

import java.util.List;

public record SurveyBody(
        Integer daysToPlay,
        List<Long> startEndPoints,          // spot_ids
        List<String> trafficModes,
        Integer spotsPerDay,
        Budget budget
) {
}
