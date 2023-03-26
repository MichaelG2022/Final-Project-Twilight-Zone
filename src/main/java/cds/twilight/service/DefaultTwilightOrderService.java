package cds.twilight.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cds.twilight.dao.TwilightOrderDao;
import cds.twilight.entity.Accessory;
import cds.twilight.entity.Customer;
import cds.twilight.entity.Fixture;
import cds.twilight.entity.Lamp;
import cds.twilight.entity.Lens;
import cds.twilight.entity.Order;
import cds.twilight.entity.OrderRequest;

/*
 * Service annotation tells Spring Boot that this class is designated as a service class.
 * 
 * Autowired annotation tells Spring to use dependency injection to wire TwilightOrderService to
 * this class.
 */
@Service
public class DefaultTwilightOrderService implements TwilightOrderService {
  
  @Autowired
  private TwilightOrderDao twilightOrderDao;

  @Transactional
  @Override
  public Order createLightOrder(@Valid @NotNull OrderRequest orderRequest) {
    Customer customer = getCustomer(orderRequest);
    Fixture fixture = getFixture(orderRequest);
    Lens lens = getLens(orderRequest);
    Lamp lamp = getLamp(orderRequest);
    List<Accessory> accessories = getAccessory(orderRequest);
    BigDecimal price =
        fixture.getPrice().add(lens.getPrice()).add(lamp.getPrice());
    
    for(Accessory accessory : accessories) {
      price = price.add(accessory.getPrice());      
    } // end FOR

    return twilightOrderDao.saveOrder(customer, fixture, lens, lamp, price, accessories);
  } // end createOrder

  private List<Accessory> getAccessory(OrderRequest orderRequest) {
    return twilightOrderDao.fetchAccessories(orderRequest.getAccessories());
  } // end getOption

  protected Customer getCustomer(OrderRequest orderRequest) {
    return twilightOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException(
            "Customer with ID=" + orderRequest.getCustomer() + " was not found"));
  } // end getCustomer

  protected Fixture getFixture(OrderRequest orderRequest) {
    return twilightOrderDao.fetchFixture(orderRequest.getFixture())
        .orElseThrow(() -> new NoSuchElementException(
            "Fixture with ID=" + orderRequest.getFixture() 
            + " was not found"));
  } // end getFixture

  protected Lens getLens(OrderRequest orderRequest) {
    return twilightOrderDao.fetchLens(orderRequest.getLens())
        .orElseThrow(() -> new NoSuchElementException(
            "Lens with ID=" + orderRequest.getLens() + " was not found"));
  } // end getLens

  protected Lamp getLamp(OrderRequest orderRequest) {
    return twilightOrderDao.fetchLamp(orderRequest.getLamp())
        .orElseThrow(() -> new NoSuchElementException(
            "Lamp with ID=" + orderRequest.getLamp() + " was not found"));
  } // end getLamp

} // end CLASS
