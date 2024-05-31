package com.example.school.calculate_distancesTest;

import org.junit.jupiter.api.Test;

import static com.example.school.calculate_distances.CalculateDistances.distanceInKilometerByHaversine;

public class CalculateDistancesTest {

    @Test
    public void 거리계산() {
        double result = distanceInKilometerByHaversine(35.85406277, 128.6028415, 35.846875, 128.582727);
        System.out.println("거리계산:" + result);
    }
}
