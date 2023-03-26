package cds.twilight.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cds.twilight.dao.TwilightLensDao;
import cds.twilight.entity.Lens;
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
public class DefaultTwilightLensService implements TwilightLensService {
  
  @Autowired
  private TwilightLensDao twilightLensDao; 

  @Transactional
  @Override
  public List<Lens> fetchLenses(String lensColor) {
    log.debug("Lens list request in Service");
    List<Lens> lenses = twilightLensDao.fetchLenses(lensColor);
    
    if(lenses.isEmpty()) {
      String msg = String.format("No lenses were found with lens color=%s", lensColor);      
      throw new NoSuchElementException(msg);
    } // end IF
    
    Collections.sort(lenses);
    
    return lenses;
    
  } // end fetchCustomers

} // end CLASS
