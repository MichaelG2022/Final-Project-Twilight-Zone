package cds.twilight.dao;

import java.util.List;
import cds.twilight.entity.Lamp;

public interface TwilightLampDao {

  List<Lamp> fetchLamps(int wattage);

} // end INTERFACE
