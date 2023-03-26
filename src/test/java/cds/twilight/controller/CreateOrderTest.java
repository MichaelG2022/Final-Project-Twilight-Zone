package cds.twilight.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import cds.twilight.controller.support.CreateOrderTestSupport;
import cds.twilight.entity.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
// @formatter:off
@Sql(
    scripts = {"classpath:flyway/migrations/twilight_schema.sql",
               "classpath:flyway/migrations/twilight_data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
// @formatter:on
public class CreateOrderTest extends CreateOrderTestSupport {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

//********************************************************************************************
// ORDER CREATION TEST - successful/OK - 200 status code
//********************************************************************************************
  @Test
  void testCreateLightOrderReturnsSuccess201() {
    // Given: an order as JSON
    String body = createLightOrderBody();
    String uri = getBaseUriForOrders();

    // Counting row before the test so we can verify that we added 1 row to orders table and 6 rows
    // to options table with the test.

    int numRowsOrders = JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders");
    int numRowsAccessories = JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_accessories");
    
    // ADDED FOR MY TEST
    System.out.println(numRowsOrders);
    System.out.println(numRowsAccessories);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

    // When: the order is sent
    ResponseEntity<Order> response =
        getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

    // Then: a 201 status is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // And: the returned order is correct
    assertThat(response.getBody()).isNotNull();

    // This adds the body as a variable named order, then compares that body to the data we added to
    // the JSON.

    Order order = response.getBody();
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("YOUNG_SUE");
    assertThat(order.getFixture().getFixtureId()).isEqualTo("ETC_S4_SR_BLACK_10_90_DEG_EDISON");
    assertThat(order.getLens().getLensId()).isEqualTo("ETC_S4_SR_BLACK_36_DEG");
    assertThat(order.getLamp().getLampId()).isEqualTo("HPL_LAMP_750W_120V_2000H");    
    assertThat(order.getAccessories()).hasSize(4);

    // Counting rows again to verify the test added 1 row to order table and 6 rows to options table
    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders")).isEqualTo(numRowsOrders + 1);
    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_accessories"))
        .isEqualTo(numRowsAccessories + 4);
    
 // ADDED FOR MY TEST
    System.out.println(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders"));
    System.out.println(JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_accessories"));

  } // end testCreateLightOrderReturnsSuccess201

} // end CLASS
