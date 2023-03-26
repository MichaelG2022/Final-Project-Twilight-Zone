package cds.twilight.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cds.twilight.dao.TwilightLampDao;
import cds.twilight.entity.Lamp;
import lombok.extern.slf4j.Slf4j;

/*
 * Service annotation tells Spring Boot that this class is designated as a service class.
 * 
 * Slf4j annotation enables Lombok logging. The info log line is to show when this service class is
 * called during testing.
 * 
 * Autowired annotation tells Spring to use dependency injection to wire TwilightOrderService to
 * this class.
 */
@Service
@Slf4j
public class DefaultTwilightLampService implements TwilightLampService {
  
  @Autowired
  private TwilightLampDao twilightLampDao;  

  @Transactional
  @Override
  public List<Lamp> fetchLamps(int wattage) {
    log.debug("Lamp list request in Service");
    List<Lamp> lamps = twilightLampDao.fetchLamps(wattage);
    
    if(lamps.isEmpty()) {
      String msg = String.format("No lamps were found");      
      throw new NoSuchElementException(msg);
    } // end IF
    
    Collections.sort(lamps);
    
    return lamps;
    
  } // end fetchCustomers

} // end CLASS