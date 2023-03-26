package cds.twilight.dao;

import java.util.List;
import cds.twilight.entity.Lens;

public interface TwilightLensDao {

  List<Lens> fetchLenses(String lensColor);

} // end INTERFACE
