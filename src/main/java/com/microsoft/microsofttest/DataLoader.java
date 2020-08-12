package com.microsoft.microsofttest;

import com.infomatiq.jsi.Rectangle;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.security.auth.message.config.AuthConfigProvider;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.spi.FileSystemProvider;
import java.util.Iterator;

@Component
public class DataLoader {

  private final LocationDataStore locationDataStore;
  private final TruckDataStore truckDataStore;
  private final ConfigProvider configProvider;

  public DataLoader(LocationDataStore locationDataStore, TruckDataStore truckDataStore, ConfigProvider configProvider) {
    this.locationDataStore = locationDataStore;
    this.truckDataStore = truckDataStore;
    this.configProvider = configProvider;
  }

  // method to load the csv file into our datastores
  @PostConstruct
  public void loaddata() throws IOException {
    File file = ResourceUtils.getFile(configProvider.getFilePath());
    Reader in = new FileReader(file.getPath());
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(
        "locationid", "Applicant", "FacilityType", "cnn", "LocationDescription", "Address", "blocklot", "block", "lot",
        "permit", "Status", "FoodItems", "X", "Y", "Latitude", "Longitude", "Schedule",
        "dayshours", "NOISent", "Approved", "Received", "PriorPermit", "ExpirationDate",
        "Location", "Fire Prevention Districts", "Police Districts",
        "Supervisor Districts", "Zip Codes", "Neighborhoods (old)"
    ).parse(in);

    Iterator<CSVRecord> iterator = records.iterator();
    iterator.next();
    // use our own ids because of how atomic integers work, we can't have a 0 for an id, so can't rely on outside sources.
    int i = 0;
    while (iterator.hasNext()) {
      CSVRecord record = iterator.next();
      FoodTruck truck = new FoodTruck(Float.parseFloat(record.get("Latitude")), Float.parseFloat(record.get("Longitude")),
          record.get("Address"), record.get("FoodItems"), ++i);
      //add food truck to datastore
      truckDataStore.addTruck(truck.getId(), truck);

      final Rectangle rect = new Rectangle(truck.getLat(), truck.getLon(),
          truck.getLat(), truck.getLon());
      //add food truck location + id to location data store.
      locationDataStore.getSpatialIndex().add(rect, truck.getId());
    }
  }
}