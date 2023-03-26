package cds.twilight.dao;

import java.util.List;
import cds.twilight.entity.Fixture;

public interface TwilightFixtureDao {

  List<Fixture> fetchFixtures(String color);

} // end INTERFACE
