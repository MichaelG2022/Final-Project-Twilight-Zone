package cds.twilight.service;

import java.util.List;
import cds.twilight.entity.Fixture;

public interface TwilightFixtureService {

  List<Fixture> fetchFixtures(String color); 

} // end INTERFACE
