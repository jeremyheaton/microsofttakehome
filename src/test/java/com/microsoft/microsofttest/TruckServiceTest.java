package com.microsoft.microsofttest;

import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TruckServiceTest {

  @Test
  public void testTruckServiceReturnClosest5Points() {
    TruckDataStore truckDataStore = new TruckDataStore();
    LocationDataStore locationDataStore = new LocationDataStore();
    SpatialIndex spatialIndex = locationDataStore.getSpatialIndex();
    spatialIndex.add(new Rectangle(0,0,0,0), 6);
    spatialIndex.add(new Rectangle(0,0,0,0), 1);
    spatialIndex.add(new Rectangle(0,0,0,0), 2);
    spatialIndex.add(new Rectangle(0,0,0,0), 3);
    spatialIndex.add(new Rectangle(0,0,0,0), 4);
    spatialIndex.add(new Rectangle(3,0,3,0), 5);
    Set<FoodTruck> expectedTrucks = new HashSet<>();
    for(int i = 1; i<=5; i++) {
      FoodTruck testTruck = new FoodTruck(0,0,"","", i);
      truckDataStore.addTruck(i, testTruck);
      expectedTrucks.add(testTruck);
    }
    TruckService truckService = new TruckService(locationDataStore, truckDataStore);
    List<FoodTruck> foodTrucks = truckService.getTrucks(0,0);

    for(FoodTruck foodTruck : foodTrucks) {
      assertTrue(expectedTrucks.contains(foodTruck));
    }
  }

  @Test
  public void testTruckServiceLessThan5InTree() {
    TruckDataStore truckDataStore = new TruckDataStore();
    LocationDataStore locationDataStore = new LocationDataStore();
    SpatialIndex spatialIndex = locationDataStore.getSpatialIndex();
    spatialIndex.add(new Rectangle(0,0,0,0), 3);
    spatialIndex.add(new Rectangle(0,0,0,0), 1);
    spatialIndex.add(new Rectangle(0,0,0,0), 2);

    for(int i = 1; i<=3; i++) {
      FoodTruck testTruck = new FoodTruck(0,0,"","", i);
      truckDataStore.addTruck(i, testTruck);
    }
    TruckService truckService = new TruckService(locationDataStore, truckDataStore);
    List<FoodTruck> foodTrucks = truckService.getTrucks(0,0);

    assertEquals(3, foodTrucks.size());
  }
}
