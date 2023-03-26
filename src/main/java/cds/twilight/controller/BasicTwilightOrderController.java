package cds.twilight.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import cds.twilight.entity.Order;
import cds.twilight.entity.OrderRequest;
import cds.twilight.service.TwilightOrderService;
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
public class BasicTwilightOrderController implements TwilightOrderController {

  @Autowired
  private TwilightOrderService twilightOrderService;


  @Override
  public Order createLightOrder(@Valid @NotNull OrderRequest orderRequest) {
    log.debug("Order={}", orderRequest);
    return twilightOrderService.createLightOrder(orderRequest);
  } // end createOrder

} // end CLASS
