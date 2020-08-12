package com.microsoft.microsofttest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TruckService {

  private final LocationDataStore locationDataStore;
  private final TruckDataStore truckDataStore;

  public TruckService(LocationDataStore locationDataStore, TruckDataStore truckDataStore) {
    this.locationDataStore = locationDataStore;
    this.truckDataStore = truckDataStore;
  }

  //Query the location datastore and then collect the ids, use them to access the truck datastore and return the trucks
  public List<FoodTruck> getTrucks(float lat, float lon) {
    List<FoodTruck> trucks = new ArrayList<>();
    List<AtomicInteger> atomicIntegers = locationDataStore.search(lat, lon);
    for(AtomicInteger atomicInteger : atomicIntegers) {
      trucks.add(truckDataStore.getTruck(atomicInteger.get()));
    }
    return trucks.stream().filter(Objects::nonNull).collect(Collectors.toList());
  }

}
