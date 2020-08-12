# Running the application
I provide two ways to run the application. The first being a deployable artifact location in the target directory. The second being a maven command to run the source. 

From the root directory you can run either
```
java -jar target/microsofttest-0.0.1-SNAPSHOT.jar
```
Or
```
mvn spring-boot:run
```

Once the application boots up you can access its single endpoint at
``` 
localhost:8080/trucks?lat={latValue}&lon={lonValue}
```

The current implementation will always the closest 5 food trucks ("not really, it assumes lat and lon are on a 2d plane"). A typical response will look like so
```json
[
  {
    "lat": 37.78754,
    "lon": -122.39773,
    "address": "564 HOWARD ST",
    "name": "Tortas: Burritos: Tacos: Churros: Nachos: Asada Fries",
    "id": 1447794
  },
  {
    "lat": 37.787304,
    "lon": -122.39804,
    "address": "580 HOWARD ST",
    "name": "Mexican Food",
    "id": 1367290
  },
  {
    "lat": 37.787956,
    "lon": -122.39724,
    "address": "540 HOWARD ST",
    "name": "Tacos: burritos: quesadillas",
    "id": 1339633
  },
  {
    "lat": 37.7868,
    "lon": -122.39787,
    "address": "201 02ND ST",
    "name": "Prepackaged Kettlecorn",
    "id": 1353225
  },
  {
    "lat": 37.788445,
    "lon": -122.39864,
    "address": "555 MISSION ST",
    "name": "Noodles",
    "id": 1408986
  }
]
```

The implementation uses a spatial library called JSI, I've worked with some other spatial libraries in the past but I've never used this one. I picked it specifically because of it's speed. There are a few problems though. One pitfall is that that it assumes a 2d plane, so it works well with coordinates that are very close together, like a city, but would not work well with a country or whole planet. 

Another problem is how the library is implemented. The calculations each run on different threads which creates a problem for a webservice, because I need to collect all the results manually. This creates code that is hard to test and is overly complicated.

Doing the exercise again, I would opt for a different library I'ved used in the past. One that accounts for earth curvatures.

One problem I didn't get to solve due to time is the data. Not all of it is "good" data. There are duplicate locations, revoked permits, different schedules for different locations. That would all need to be accounted for in a proper search.