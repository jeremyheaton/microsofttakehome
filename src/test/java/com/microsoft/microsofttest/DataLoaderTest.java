package com.microsoft.microsofttest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DataLoaderTest {

	@Test
	public void testDataLoader() throws IOException {
		String path = "src/test/resources/Mobile_Food_Facility_Permit.csv";
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();

		ConfigProvider configProvider = new ConfigProvider(absolutePath);
		LocationDataStore locationDataStore = new LocationDataStore();
		TruckDataStore truckDataStore = new TruckDataStore();
		DataLoader dataLoader = new DataLoader(locationDataStore, truckDataStore, configProvider);
		dataLoader.loaddata();
		assertTrue(truckDataStore.containsTruck(1));
		assertTrue(truckDataStore.containsTruck(2));

	}

}
