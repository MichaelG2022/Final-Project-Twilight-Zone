package cds.twilight.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import cds.twilight.entity.Accessory;
import cds.twilight.entity.Customer;
import cds.twilight.entity.Fixture;
import cds.twilight.entity.Lamp;
import cds.twilight.entity.Lens;
import cds.twilight.entity.Order;

/*
 * Component annotation tells Spring to pay attention to this class.
 */
@Component
public class DefaultTwilightOrderDao implements TwilightOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Order saveOrder(Customer customer, Fixture fixture, Lens lens, Lamp lamp,
      BigDecimal totalPrice, List<Accessory> accessories) {

    /*
     * Method call to generate the Sql for saving the order fields and current parameter values into
     * the orders table.
     */
    SqlParams params = generateSaveOrderInsertSql(customer, fixture, lens, lamp, totalPrice);

    // keyholder catches orderPK after calling update so we can save the order accessories.
    KeyHolder keyHolder = new GeneratedKeyHolder();

    // Doing SQL update to save the order.
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    // Extracts the orderPK from keyHolder to use for saving the order accessories.
    int orderPK = keyHolder.getKey().intValue();

    saveAccessories(accessories, orderPK);

    // @formatter:off
    return Order.builder()
        .orderPK(orderPK)
        .customer(customer)
        .fixture(fixture)
        .lens(lens)
        .lamp(lamp)
        .accessories(accessories)
        .totalPrice(totalPrice)
        .build(); 
    // @formatter:on
  } // end saveOrder

  // Generate Insert SQL for saveOrder
  private SqlParams generateSaveOrderInsertSql(Customer customer, Fixture fixture, Lens lens,
      Lamp lamp, BigDecimal totalPrice) {
    // Instantiating a new MapSqlParameterSource object
    SqlParams params = new SqlParams();
    // @formatter:off
    params.sql = ""
        + "INSERT INTO orders (customer_fk, fixture_fk, lens_fk, lamp_fk, total_Price) "
        + "VALUES "
        + "(:customer_fk, :fixture_fk, :lens_fk, :lamp_fk, :total_Price)";        
     // @formatter:on

    // Adding PK values for the FK values in the orders table. PK in entity tables is an FK in the
    // orders table.
    params.source.addValue("customer_fk", customer.getCustomerPK());
    params.source.addValue("fixture_fk", fixture.getFixturePK());
    params.source.addValue("lens_fk", lens.getLensPK());
    params.source.addValue("lamp_fk", lamp.getLampPK());
    params.source.addValue("total_Price", totalPrice);

    return params;
  } // end generateSaveOrderInsertSql

  private void saveAccessories(List<Accessory> accessories, int orderPK) {
    for (Accessory accessory : accessories) {
      SqlParams params = generateSaveAccessoriesInsertSql(accessory, orderPK);
      jdbcTemplate.update(params.sql, params.source);
    } // end FOR
  } // end saveAccessories

  // Generate Insert SQL for saveAccessories
  private SqlParams generateSaveAccessoriesInsertSql(Accessory accessory, int orderPK) {
    // Instantiating a new MapSqlParameterSource object
    SqlParams params = new SqlParams();

    // @formatter:off
    params.sql = "INSERT INTO order_accessories (accessory_fk, order_fk) "
        + "VALUES "
        + "(:accessory_fk, :order_fk)";
    // @formatter:on

    params.source.addValue("accessory_fk", accessory.getAccessoryPK());
    params.source.addValue("order_fk", orderPK);

    return params;
  } // end generateSaveAccessoriesInsertSql

  @Override
  public List<Accessory> fetchAccessories(List<String> accessoryIds) {
    // Checks for empty optionIds (still not checking for null optionIds)
    // Returns an empty list if optionIds is empty
    if (accessoryIds.isEmpty()) {
      return new LinkedList<>();
    } // end IF

    Map<String, Object> params = new HashMap<>();

    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM accessories "
        + "WHERE accessory_id IN(";
    // @formatter:on

    /*
     * The following code adds to the sql statement after the IN(
     * 
     * Now that we know options aren't empty, we will cycle through the options to create the
     * :accessory key value to add to the sql statement, then will add the actual value into the map
     * with the key.
     * 
     * We create the key without colons so it doesn't interfere with the Map<K,V>.
     * 
     * We add a colon to the key to create a named parameter for use in the sql statement. We also
     * add a comma and a space to the end of the key for proper sql inside the IN parameter area.
     * 
     * The sql statement will then look like this: SELECT * FROM accessories WHERE accessory_id
     * IN(:accessory_0, :accessory_1, etc.)
     * 
     * Then we use the key without colons to get the option value to put into the params Map.
     */
    for (int index = 0; index < accessoryIds.size(); index++) {
      // Creating the key for the Map. Key will look like "accessory_0"
      String key = "accessory_" + index;
      // Adding the colon to the key for named parameter sql statement and a comma and space at the
      // end.
      sql += ":" + key + ", ";
      params.put(key, accessoryIds.get(index));
    } // end FOR

    /*
     * Since we added a comma and space to the end of every accessory, we have to remove the comma
     * and space from the last accessory.
     * 
     * We do this by getting the length of sql and subtracting 2 from it to drop the last 2
     * characters.
     */
    sql = sql.substring(0, sql.length() - 2);

    /*
     * Then we have to add a closing parenthesis to finish the sql IN(
     */
    sql += ")";

    return jdbcTemplate.query(sql, params, new RowMapper<Accessory>() {

      @Override
      public Accessory mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Accessory.builder()
            .accessoryPK(rs.getInt("accessory_pk"))
            .accessoryId(rs.getString("accessory_id"))
            .name(rs.getString("name"))
            .manufacturer(rs.getString("manufacturer"))
            .description(rs.getString("description"))
            .price(rs.getBigDecimal("price"))
            .build();
        // @formatter:on
      } // end mapRow
    } // end RowMapper
    ); // end jdbcTemplate.query
  } // end fetchAccessories

  @Override
  public Optional<Customer> fetchCustomer(String customerId) {
    // if( ) {
    // Customer customer = null;
    // return Optional.ofNullable(customer);
    // } else {
 // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
    // }
  } // end fetchCustomer

  class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {

    // Unimplemented method of ResultSetExtractor interface
    @Override
    public Customer extractData(ResultSet rs) throws SQLException {
      // Result set comes to us in initialized state which means the result set pointer is pointing
      // before the first line of the result set (as in index -1) so we have to use rs.next() to get
      // it to move to the first line of the result set.
      rs.next();

    // @formatter:off
    return Customer.builder()
        .customerPK(rs.getInt("customer_pk"))
        .customerId(rs.getString("customer_id"))
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .phone(rs.getString("phone"))          
        .build();
    // @formatter:on
    } // end extractData
  } // end INNER CLASS CustomerResultSetExtractor

  @Override
  public Optional<Fixture> fetchFixture(String fixtureId) {
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM fixtures "
        + "WHERE fixture_id = :fixture_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("fixture_id", fixtureId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new FixtureResultSetExtractor()));
  } // end fetchFixture

  class FixtureResultSetExtractor implements ResultSetExtractor<Fixture> {

    // Unimplemented method of ResultSetExtractor interface
    @Override
    public Fixture extractData(ResultSet rs) throws SQLException {
      // Result set comes to us in initialized state which means the result set pointer is pointing
      // before the first line of the result set (as in index -1) so we have to use rs.next() to get
      // it to move to the first line of the result set.
      rs.next();

    // @formatter:off
    return Fixture.builder()
        .fixturePK(rs.getInt("fixture_pk"))
        .fixtureId(rs.getString("fixture_id"))
        .color(rs.getString("color"))
        .plugType(rs.getString("plug_type"))
        .description(rs.getString("description"))  
        .price(rs.getBigDecimal("price"))
        .build();
    // @formatter:on
    } // end extractData
  } // end INNER CLASS FixtureResultSetExtractor

  @Override
  public Optional<Lens> fetchLens(String lensId) {
 // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM lenses "
        + "WHERE lens_id = :lens_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("lens_id", lensId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new LensResultSetExtractor()));
  } // end fetchLens

  class LensResultSetExtractor implements ResultSetExtractor<Lens> {

    // Unimplemented method of ResultSetExtractor interface
    @Override
    public Lens extractData(ResultSet rs) throws SQLException {
      // Result set comes to us in initialized state which means the result set pointer is pointing
      // before the first line of the result set (as in index -1) so we have to use rs.next() to get
      // it to move to the first line of the result set.
      rs.next();

    // @formatter:off
    return Lens.builder()
        .lensPK(rs.getInt("lens_pk"))
        .lensId(rs.getString("lens_id"))
        .lensSize(rs.getInt("lens_size"))
        .lensColor(rs.getString("lens_color"))
        .description(rs.getString("description"))  
        .price(rs.getBigDecimal("price"))
        .build();
    // @formatter:on
    } // end extractData
  } // end INNER CLASS LensResultSetExtractor


  @Override
  public Optional<Lamp> fetchLamp(String lampId) {
    /// @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM lamps "
        + "WHERE lamp_id = :lamp_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("lamp_id", lampId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new LampResultSetExtractor()));
  } // end fetchLens

  class LampResultSetExtractor implements ResultSetExtractor<Lamp> {

    @Override
    public Lamp extractData(ResultSet rs) throws SQLException, DataAccessException {
      // Result set comes to us in initialized state which means the result set pointer is pointing
      // before the first line of the result set (as in index -1) so we have to use rs.next() to get
      // it to move to the first line of the result set.
      rs.next();

    // @formatter:off
    return Lamp.builder()
        .lampPK(rs.getInt("lamp_pk"))
        .lampId(rs.getString("lamp_id"))
        .wattage(rs.getInt("wattage"))
        .description(rs.getString("description"))  
        .price(rs.getBigDecimal("price"))
        .build();
    // @formatter:on
    } // end extractData
  } // end INNER CLASS LensResultSetExtractor

  // MapSqlParameterSource passes in a simple map of parameter values to the methods of the
  // NamedParameterJdbcemplate class.
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  } // end SqlParams

} // end CLASS
