package cds.twilight.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import cds.twilight.entity.Lamp;
import cds.twilight.service.TwilightLampService;
import lombok.extern.slf4j.Slf4j;

/*
 * RestController annotation tells Spring Boot that the controller interface this controller class
 * implements is a REST controller. This cannot be done in the interface; it must be done in a
 * class. The implementation of TwilightOrderController means Spring Boot will then go look in that
 * Interface.
 * 
 * Slf4j annotation enables Lombok logging. Log entry is made at info level showing the model and
 * trim of the jeep model being requested
 * 
 * Autowired annotation tells Spring to use dependency injection to wire TwilightOrderService to
 * this class.
 */
@RestController
@Slf4j
public class BasicTwilightLampController implements TwilightLampController {
  
  @Autowired
  private TwilightLampService twilightLampService;

  @Override
  public List<Lamp> fetchLamps(int wattage) {
    log.debug("Lamp list request in Controller");
    return twilightLampService.fetchLamps(wattage);
  }

  

} // end CLASS
