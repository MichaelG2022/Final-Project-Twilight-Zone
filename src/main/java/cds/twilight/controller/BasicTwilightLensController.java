package cds.twilight.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import cds.twilight.entity.Lens;
import cds.twilight.service.TwilightLensService;
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
public class BasicTwilightLensController implements TwilightLensController {

  @Autowired
  private TwilightLensService twilightLensService;

  @Override
  public List<Lens> fetchLenses(String lensColor) {
    log.debug("Lens list request in Controller");
    return twilightLensService.fetchLenses(lensColor);
  }

} // end CLASS
