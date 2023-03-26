package cds.twilight.controller.support;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import cds.twilight.entity.Lamp;
import lombok.Getter;

public class FetchLampTestSupport {
  
  @LocalServerPort
  private int serverPort;

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;

  protected String getBaseUriForLamps() {
    return String.format("http://localhost:%d/lamps", serverPort);
  } // end getBaseUriForLamps
  protected List<Lamp> buildExpected() {
    List<Lamp> list = new LinkedList<>();
    
  // @formatter:off
  list.add(Lamp.builder()
      .lampId("HPL_LAMP_575W_115V_300H")
      .wattage(575)
      .description("575 watt lamp, 115 volts, 300 hour life")
      .price(new BigDecimal("16.89"))
      .build());
  
  list.add(Lamp.builder()
      .lampId("HPL_LAMP_575W_115V_2000H")
      .wattage(575)
      .description("575 watt lamp, 115 volts, 2000 hour life")
      .price(new BigDecimal("16.89"))
      .build());
  
  list.add(Lamp.builder()
      .lampId("HPL_LAMP_575W_120V_300H")
      .wattage(575)
      .description("575 watt lamp, 120 volts, 300 hour life")
      .price(new BigDecimal("21.35"))
      .build());
  
  list.add(Lamp.builder()
      .lampId("HPL_LAMP_575W_120V_2000H")
      .wattage(575)
      .description("575 watt lamp, 120 volts, 2000 hour life")
      .price(new BigDecimal("24.85"))
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
