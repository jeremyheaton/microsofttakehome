package com.microsoft.microsofttest;

// food truck pojo
public class FoodTruck {

  private float lat;
  private float lon;
  private String address;
  private String name;
  private Integer id;

  public FoodTruck(float lat, float lon, String address, String name, Integer id) {
    this.lat = lat;
    this.lon = lon;
    this.address = address;
    this.name = name;
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public float getLat() {
    return lat;
  }

  public void setLat(float lat) {
    this.lat = lat;
  }

  public float getLon() {
    return lon;
  }

  public void setLon(float lon) {
    this.lon = lon;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
