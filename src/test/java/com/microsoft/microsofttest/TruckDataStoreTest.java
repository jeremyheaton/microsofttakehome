package com.microsoft.microsofttest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TruckDataStoreTest {

	@Test
	public void testTruckDataStoreId0() throws IOException {
		TruckDataStore truckDataStore = new TruckDataStore();
		truckDataStore.addTruck(0, new FoodTruck(0,0,"","", 0));
		assertNull(truckDataStore.getTruck(0));
	}

	@Test
	public void testTruckDataStore() throws IOException {
		TruckDataStore truckDataStore = new TruckDataStore();
		truckDataStore.addTruck(1, new FoodTruck(0,0,"","", 0));
		assertNotNull(truckDataStore.getTruck(1));
	}

}
