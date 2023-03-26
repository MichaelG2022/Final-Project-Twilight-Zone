package cds.twilight.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cds.twilight.dao.TwilightAccessoryDao;
import cds.twilight.entity.Accessory;
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
public class DefaultTwilightAccessoryService implements TwilightAccessoryService {

  @Autowired
  private TwilightAccessoryDao twilightAccessoryDao;

  @Transactional
  @Override
  public List<Accessory> fetchAccessories() {
    log.debug("Accessory list request in Service");
    List<Accessory> accessories = twilightAccessoryDao.fetchAccessories();

    if (accessories.isEmpty()) {
      String msg = String.format("No accessories were found");
      throw new NoSuchElementException(msg);
    } // end IF

    Collections.sort(accessories);

    return accessories;
  } // end fetchAccessories

} // end CLASS
