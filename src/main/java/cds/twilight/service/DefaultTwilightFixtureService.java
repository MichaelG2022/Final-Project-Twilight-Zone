package cds.twilight.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cds.twilight.dao.TwilightFixtureDao;
import cds.twilight.entity.Fixture;
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
public class DefaultTwilightFixtureService implements TwilightFixtureService {
  
  @Autowired
  private TwilightFixtureDao twilightFixtureDao;  

  @Transactional
  @Override
  public List<Fixture> fetchFixtures(String color) {
    log.debug("Fixture list request in Service");
    List<Fixture> fixtures = twilightFixtureDao.fetchFixtures(color);
    
    if(fixtures.isEmpty()) {
      String msg = String.format("No fixtures were found");      
      throw new NoSuchElementException(msg);
    } // end IF
    
    Collections.sort(fixtures);
    
    return fixtures;
    
  } // end fetchCustomers

} // end CLASS