package com.microsoft.microsofttest;

import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class LocationDataStoreTest {

  @Test
  public void testSearch() {
    LocationDataStore locationDataStore = new LocationDataStore();
    SpatialIndex spatialIndex = locationDataStore.getSpatialIndex();
    spatialIndex.add(new Rectangle(0,0,0,0), 5);
    spatialIndex.add(new Rectangle(0,0,0,0), 1);
    spatialIndex.add(new Rectangle(0,0,0,0), 2);
    spatialIndex.add(new Rectangle(0,0,0,0), 3);
    spatialIndex.add(new Rectangle(0,0,0,0), 4);

    List<Integer> expectedOutput = IntStream.rangeClosed(1,5).boxed().collect(Collectors.toList());
    List<AtomicInteger> atomicIntegers = locationDataStore.search(0,0);
    List<Integer> integers = atomicIntegers.stream().map(AtomicInteger::get).sorted().collect(Collectors.toList());
    assertEquals(expectedOutput, integers);

  }
}
