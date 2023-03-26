package cds.twilight.service;

import java.util.List;
import cds.twilight.entity.Lamp;

public interface TwilightLampService {

  List<Lamp> fetchLamps(int wattage);

} // end INTERFACE