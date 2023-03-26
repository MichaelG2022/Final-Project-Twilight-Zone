package cds.twilight.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cds.twilight.dao.TwilightCustomerDao;
import cds.twilight.entity.Customer;
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
public class DefaultTwilightCustomerService implements TwilightCustomerService {

  @Autowired
  private TwilightCustomerDao twilightCustomerDao;

  @Transactional
  @Override
  public List<Customer> fetchCustomers(String lastName) {
    log.debug("Customer list request in Service");
    List<Customer> customers = twilightCustomerDao.fetchCustomers(lastName);

    if (customers.isEmpty()) {
      String msg = String.format("No customers were found with last name=%s", lastName);
      throw new NoSuchElementException(msg);
    } // end IF

    Collections.sort(customers);

    return customers;

  } // end fetchCustomers

  @Transactional
  @Override
  public Optional<Customer> createCustomer(String customerId, String firstName, String lastName,
      String phone) {
    log.debug("Create customer in Service: customerId={}, firstName={}, lastName={}, phone={}",
        customerId, firstName, lastName, phone);
    return twilightCustomerDao.createCustomer(customerId, firstName, lastName, phone);
  }

  @Override
  public Optional<Customer> updateCustomer(String customerId, String firstName, String newFirstName,
      String lastName, String newLlastName, String phone, String newPhone) {
    log.debug(
        "Update customer in Service: customerId={}, firstName={}, newFirstName={},"
            + " lastName={}, newLastName={}, phone={}, newPhone={}",
        customerId, firstName, newFirstName, lastName, newLlastName, phone, newPhone);
    return twilightCustomerDao.updateCustomer(customerId, firstName, newFirstName, lastName,
        newLlastName, phone, newPhone);
  } // end updateCustomer

  @Override
  public Optional<Customer> deleteCustomer(String customerId, String firstName, String lastName,
      String phone) {
    log.debug("Delete customer in Service: customerId={}, firstName={}, lastName={}, phone={}",
        customerId, firstName, lastName, phone);
    return twilightCustomerDao.deleteCustomer(customerId, firstName, lastName, phone);
  } // end deleteCustomer
} // end CLASS
