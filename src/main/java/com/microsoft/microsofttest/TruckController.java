package com.microsoft.microsofttest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TruckController {

  private final TruckService truckService;

  public TruckController(TruckService truckService) {
    this.truckService = truckService;
  }

  // controller for trucks, not much to it.
  @GetMapping("/trucks")
  public List<FoodTruck> getTrucks(@RequestParam("lat") float lat, @RequestParam("lon") float lon){
    return truckService.getTrucks(lat, lon);
  }

}
