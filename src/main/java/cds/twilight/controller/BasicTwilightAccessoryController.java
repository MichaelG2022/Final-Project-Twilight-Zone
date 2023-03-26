package cds.twilight.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import cds.twilight.entity.Accessory;
import cds.twilight.service.TwilightAccessoryService;
import lombok.extern.slf4j.Slf4j;

/*
 * RestController annotation tells Spring Boot that the controller interface this controller class
 * implements is a REST controller. This cannot be done in the interface; it must be done in a
 * class. Spring Boot will then go look in the implementing class.
 * 
 * Slf4j annotation enables Lombok logging. Log entry is made at debug level showing the status of
 * the request being made.
 * 
 * Autowired annotation tells Spring to use dependency injection to wire the named class to
 * this class.
 */
@RestController
@Slf4j
public class BasicTwilightAccessoryController implements TwilightAccessoryController {

  @Autowired
  private TwilightAccessoryService twilightAccessoryService;

  @Override
  public List<Accessory> fetchAccessories() {
    log.debug("Accessory list request in Controller");
    return twilightAccessoryService.fetchAccessories();
  } // end TwilightAccessoryService

} // end CLASS
