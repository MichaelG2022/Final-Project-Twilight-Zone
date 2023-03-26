package cds.twilight.service;

import java.util.List;
import cds.twilight.entity.Lens;

public interface TwilightLensService {

  List<Lens> fetchLenses(String lensColor);

} // end INTERFACE
