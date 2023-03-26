package cds.twilight.dao;

import java.util.List;
import cds.twilight.entity.Accessory;

public interface TwilightAccessoryDao {

  List<Accessory> fetchAccessories();

}
