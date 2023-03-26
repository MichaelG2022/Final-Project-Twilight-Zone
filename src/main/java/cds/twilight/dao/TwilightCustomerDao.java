package cds.twilight.dao;

import java.util.List;
import java.util.Optional;
import cds.twilight.entity.Customer;

public interface TwilightCustomerDao {

  List<Customer> fetchCustomers(String lastName);

  Optional<Customer> createCustomer(String customerId, String firstName, String lastName, String phone);

  Optional<Customer> updateCustomer(String customerId, String firstName, String newFirstName,
      String lastName, String newLlastName, String phone, String newPhone);

  Optional<Customer> deleteCustomer(String customerId, String firstName, String lastName,
      String phone);

} // end INTERFACE
