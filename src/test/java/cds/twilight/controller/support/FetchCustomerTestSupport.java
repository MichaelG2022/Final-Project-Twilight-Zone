package cds.twilight.controller.support;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import cds.twilight.entity.Customer;
import lombok.Getter;

public class FetchCustomerTestSupport {

  @LocalServerPort
  private int serverPort;

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;

  protected String getBaseUriForCustomers() {
    return String.format("http://localhost:%d/customers", serverPort);
  } // end getBaseUriForCustomers

  protected List<Customer> buildExpected() {
    List<Customer> list = new LinkedList<>();
    
  // @formatter:off
  list.add(Customer.builder()
      .customerId("YOUNG_SUE")
      .firstName("Sue")
      .lastName("Young")
      .phone("256.328.4011")
      .build());
  
  list.add(Customer.builder()
      .customerId("YOUNG_KIKKY")
      .firstName("Kikky")
      .lastName("Young")
      .phone("713.388.3663")
      .build());
  // @formatter:on

    Collections.sort(list);
    return list;
  } // end buildExpected
  
//  protected void assertErrorMessageValid(Map<String, Object> error, HttpStatus status) {
//    // @formatter:off
//    assertThat(error)
//        .containsKey("message")
//        .containsEntry("status code", status.value())
//        .containsEntry("uri", "/jeeps")
//        .containsKey("timestamp")
//        .containsEntry("reason", status.getReasonPhrase());
//    // @formatter:on    
//   } // end assertErrorMessageValid

} // end CLASS
