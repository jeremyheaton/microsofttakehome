package com.microsoft.microsofttest;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TruckDataStore {

  private final Map<Integer, FoodTruck> foodTrucks = new HashMap<>();

  public FoodTruck getTruck(Integer integer) {
    return foodTrucks.get(integer);
  }

  public boolean containsTruck(Integer key) {
    return foodTrucks.containsKey(key);
  }

  public void addTruck(Integer key, FoodTruck value) {
    if(key !=0) {
      foodTrucks.put(key, value);
    }
  }
}
