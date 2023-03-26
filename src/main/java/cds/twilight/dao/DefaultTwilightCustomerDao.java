package cds.twilight.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import cds.twilight.entity.Customer;
import lombok.extern.slf4j.Slf4j;

/*
 * Component annotation tells Spring to pay attention to this class.
 * 
 * Slf4j annotation enables Lombok logging. Log entry is made at debug level showing the status of
 * the request being made.
 */
@Component
@Slf4j
public class DefaultTwilightCustomerDao implements TwilightCustomerDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Customer> fetchCustomers(String lastName) {
    log.debug("DAO: Customer list request: lastName={}", lastName);
    // @formatter:off
    
    // Returns all data or specified parameters.
    if(lastName.equalsIgnoreCase("allall")) {
      
      String sql = ""
          + "SELECT * FROM customers "; 

      /*
       * RowMapper works with just diamond operator because fetchJeeps return
       * statement implies List of Jeeps.
       * 
       * RowMapper calls mapRow to loop through the result set and return an
       * object for each row in the result set.
       * 
       * mapRow is an unimplemented method for RowMapper that returns an object
       * for every row in the result set.
       * 
       * The Builder allows us to get values from the result set for all fields for
       * each object returned by mapRow.
       * 
       * Use column names instead of column numbers because column order might be
       * changed. It is recommended to use columns in order from left to right.
       */
      return jdbcTemplate.query(sql,
          new RowMapper<>() {

            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
              return Customer.builder()
                  .customerPK(rs.getInt("customer_pk"))
                  .customerId(rs.getString("customer_id"))
                  .firstName(rs.getString("first_name"))
                  .lastName(rs.getString("last_name"))
                  .phone(rs.getString("phone"))
                  .build();
            } // end mapRow
          } // end RowMapper
      ); // end jdbcTemplate.query
      
    } else {      
    
    String sql = ""
        + "SELECT * FROM customers "
        + "WHERE last_name = :last_name"; 
    
    Map<String, Object> params = new HashMap<>();
    params.put("last_name", lastName);

    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {

          @Override
          public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Customer.builder()
                .customerPK(rs.getInt("customer_pk"))
                .customerId(rs.getString("customer_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .phone(rs.getString("phone"))
                .build();
            // @formatter:on
            } // end mapRow
          } // end RowMapper
      ); // end jdbcTemplate.query
    } // end IF-ELSE
  } // end fetchCustomers

  @Override
  public Optional<Customer> createCustomer(String customerId, String firstName, String lastName,
      String phone) {
    log.debug("Create customer in DAO: customerId={}, firstName={}, lastName={}, phone={}",
        customerId, firstName, lastName, phone);

    // @formatter:off
    String sql =  ""
        + "INSERT INTO customers (customer_id, first_name, last_name, phone) "
        + "VALUES "
        + "(:customer_id, :first_name, :last_name, :phone)"; 
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
    params.put("first_name", firstName);
    params.put("last_name", lastName);
    params.put("phone", phone);

    jdbcTemplate.update(sql, params);

    // @formatter:off    
    return Optional.ofNullable(
        Customer.builder()
        .customerId(customerId)
        .firstName(firstName)
        .lastName(lastName)
        .phone(phone)
        .build());
    // @formatter:on

  } // end saveCustomer

  @Override
  public Optional<Customer> updateCustomer(String customerId, String firstName, String newFirstName,
      String lastName, String newLastName, String phone, String newPhone) {
    log.debug(
        "Update customer in DAO: customerId={}, firstName={}, newFirstName={},"
            + " lastName={}, newLastName={}, phone={}, newPhone={}",
        customerId, firstName, newFirstName, lastName, newLastName, phone, newPhone);

  //@formatter:off
    String sql = ""
        + "UPDATE customers "
        + "SET "
        + "phone = :new_phone, "
        + "first_name = :new_first_name, "
        + "last_name = :new_last_name "
        + "WHERE "
        + "customer_id = :customer_id "
        + "AND "
        + "first_name = :first_name "
        + "AND "
        + "last_name = :last_name";    
    //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
    params.put("first_name", firstName);
    params.put("last_name", lastName);
    params.put("phone", phone);
    params.put("new_first_name", newFirstName);
    params.put("new_last_name", newLastName);
    params.put("new_phone", newPhone);

    jdbcTemplate.update(sql, params);

 // @formatter:off    
    return Optional.ofNullable(
        Customer.builder()
        .customerId(customerId)
        .firstName(firstName)
        .lastName(lastName)
        .phone(phone)
        .build());
    // @formatter:on
  }

  @Override
  public Optional<Customer> deleteCustomer(String customerId, String firstName, String lastName,
      String phone) {
    log.debug("Delete customer in Controller: customerId={}, firstName={}, lastName={}, phone={}",
        customerId, firstName, lastName, phone);

 // @formatter:off
    String sql =  ""
        + "DELETE FROM customers "
        + "WHERE "
        + "customer_id = :customer_id "
        + "AND "
        + "first_name = :first_name "
        + "AND "
        + "last_name = last_name "
        + "AND "
        + "phone = :phone"; 
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
    params.put("first_name", firstName);
    params.put("last_name", lastName);
    params.put("phone", phone);

    jdbcTemplate.update(sql, params);

 // @formatter:off    
    return Optional.ofNullable(
        Customer.builder()
        .customerId(customerId)
        .firstName(firstName)
        .lastName(lastName)
        .phone(phone)
        .build());
    // @formatter:on
  } // end deleteCustomer
} // end CLASS
