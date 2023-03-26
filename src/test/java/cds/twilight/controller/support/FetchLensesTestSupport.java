package cds.twilight.controller.support;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import cds.twilight.entity.Lens;
import lombok.Getter;

public class FetchLensesTestSupport {
  
  @LocalServerPort
  private int serverPort;

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;

  protected String getBaseUriForLenses() {
    return String.format("http://localhost:%d/lenses", serverPort);
  } // end getBaseUriForLenses
  
  protected List<Lens> buildExpected() {
    List<Lens> list = new LinkedList<>();
    
  // @formatter:off
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_5_DEG")
      .lensSize(5)
      .lensColor("black")
      .description("Black ETC Source four 5 degree lens,"
          + " NOTE: Requires double clutch body for 5 degree lenses")
      .price(new BigDecimal("540.00"))
      .build());
  
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_10_DEG")
      .lensSize(10)
      .lensColor("black")
      .description("Black ETC Source four 10 degree lens")
      .price(new BigDecimal("472.50"))
      .build());
  
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_19_DEG")
      .lensSize(19)
      .lensColor("black")
      .description("Black ETC Source four 19 degree lens")
      .price(new BigDecimal("256.50"))
      .build());
  
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_26_DEG")
      .lensSize(26)
      .lensColor("black")
      .description("Black ETC Source four 26 degree lens")
      .price(new BigDecimal("256.50"))
      .build());
  
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_36_DEG")
      .lensSize(36)
      .lensColor("black")
      .description("Black ETC Source four 36 degree lens")
      .price(new BigDecimal("256.50"))
      .build());
  
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_50_DEG")
      .lensSize(50)
      .lensColor("black")
      .description("Black ETC Source four 50 degree lens")
      .price(new BigDecimal("256.50"))
      .build());
  
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_70_DEG")
      .lensSize(70)
      .lensColor("black")
      .description("Black ETC Source four 70 degree lens")
      .price(new BigDecimal("414.00"))
      .build());
  
  list.add(Lens.builder()
      .lensId("ETC_S4_SR_BLACK_90_DEG")
      .lensSize(90)
      .lensColor("black")
      .description("Black ETC Source four 90 degree lens")
      .price(new BigDecimal("414.00"))
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
