package cds.twilight.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import cds.twilight.entity.Customer;
import cds.twilight.service.TwilightCustomerService;
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
public class BasicTwilightCustomerController implements TwilightCustomerController {

  @Autowired
  private TwilightCustomerService twilightCustomerService;

  @Override
  public List<Customer> fetchCustomers(String lastName) {
    log.debug("Customer list request in Controller");
    return twilightCustomerService.fetchCustomers(lastName);
  }

  @Override
  public Optional<Customer> createCustomer(String customerId, String firstName, String lastName,
      String phone) {
    log.debug("Create customer in Controller: customerId={}, firstName={}, lastName={}, phone={}",
        customerId, firstName, lastName, phone);
    return twilightCustomerService.createCustomer(customerId, firstName, lastName, phone);
  } // end createCustomer

  @Override
  public Optional<Customer> updateCustomer(String customerId, String firstName, String newFirstName,
      String lastName, String newLlastName, String phone, String newPhone) {
    log.debug(
        "Update customer in Controller: customerId={}, firstName={}, newFirstName={},"
            + " lastName={}, newLastName={}, phone={}, newPhone={}",
        customerId, firstName, newFirstName, lastName, newLlastName, phone, newPhone);
    return twilightCustomerService.updateCustomer(customerId, firstName, newFirstName, lastName,
        newLlastName, phone, newPhone);
  } // end updateCustomer

  @Override
  public Optional<Customer> deleteCustomer(String customerId, String firstName, String lastName,
      String phone) {
    log.debug("Delete customer in Controller: customerId={}, firstName={}, lastName={}, phone={}",
        customerId, firstName, lastName, phone);
    return twilightCustomerService.deleteCustomer(customerId, firstName, lastName, phone);
    } // end deleteCustomer

} // end CLASS
