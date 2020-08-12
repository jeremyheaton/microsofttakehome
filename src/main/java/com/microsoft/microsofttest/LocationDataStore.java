package com.microsoft.microsofttest;

import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import com.infomatiq.jsi.rtree.RTree;
import com.spatial4j.core.distance.DistanceUtils;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class LocationDataStore {

  private final SpatialIndex si = new RTree();

  public LocationDataStore() {
    si.init(null);
  }

  public SpatialIndex getSpatialIndex() {
    return si;
  }

  public List<AtomicInteger> search(float lat, float lon) {
    List<AtomicInteger> atomicIntegers = IntStream.rangeClosed(0,4).mapToObj(i-> new AtomicInteger()).collect(Collectors.toList());
    Iterator<AtomicInteger> integerIterator = atomicIntegers.iterator();
    si.nearestN(
        new Point(lat, lon),
        i -> {
          if(integerIterator.hasNext()) {
            AtomicInteger atomicInteger = integerIterator.next();
            atomicInteger.set(i);
          }
          return true;
        },
        5,
        Float.MAX_VALUE
    );
    return atomicIntegers;
  }
}
