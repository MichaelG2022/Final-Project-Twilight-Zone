package cds.twilight.controller.support;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import cds.twilight.entity.Accessory;
import lombok.Getter;

public class FetchAccessoryTestSupport {  

  @LocalServerPort
  private int serverPort;

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;

  protected String getBaseUriForAccessories() {
    return String.format("http://localhost:%d/accessories", serverPort);
  } // end getBaseUriForAccessories
  
  protected List<Accessory> buildExpected() {
    List<Accessory> list = new LinkedList<>();
    
  // @formatter:off
  list.add(Accessory.builder()
      .accessoryId("SAFETY_CABLE_30_INCH_BLACK")
      .name("Safety cable")
      .manufacturer("The Light Source")
      .description("30\" Safety Cable made from 1/8\" aircraft cable with 5/16\" grommeted snap hook,"
          + " 200 pound load limit")
      .price(new BigDecimal("16.89"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("SAFETY_CABLE_30_INCH_WHITE")
      .name("Safety cable")
      .manufacturer("ETC")
      .description("30\" long 1/8\" diameter white wire rope cable with snap clamp fitted, "
          + "safety ratio of the cable is 5:1, breaking load is 2000lbs,"
          + " rated Safe Working Load (SWL) is 400lbs")
      .price(new BigDecimal("16.20"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("DROP_IN_IRIS_SOURCE_4")
      .name("Drop-In Iris for Source Four")
      .manufacturer("City Theatrical")
      .description("Drop-in Iris For Source Four - precise, smooth control of beam size - "
          + "specially-made leaves are tempered and heat resistant to over 1000°F - "
          + "dimensions: 4 25/32\" x 5 7/16\" x 1/2\" - 3\" Diameter iris")
      .price(new BigDecimal("107.99"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("GOBO_HOLDER_A_SIZE_FOR_S4")
      .name("Gobo holder, A size")
      .manufacturer("City Theatrical")
      .description("Gobo Holder for Source 4 A size fixtures - large heat resistant pulls - "
          + "aluminum construction acts as a heat sink to minimize pattern warpage - "
          + "Dimensions: 3 11/16\" x 3 3/16\"")
      .price(new BigDecimal("9.62"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("GOBO_HOLDER_B_SIZE_FOR_S4")
      .name("Gobo holder, B size")
      .manufacturer("City Theatrical")
      .description("Gobo Holder for Source 4 B size fixtures - large heat resistant pulls - "
          + "aluminum construction acts as a heat sink to minimize pattern warpage - "
          + "Dimensions: 3 11/16\" x 3 3/16\"")
      .price(new BigDecimal("9.62"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("TOP_HAT_FULL_BLACK")
      .name("Top hat full, black")
      .manufacturer("City Theatrical")
      .description("Black full length top hat - controls light spill and "
          + "reduces lens viewing angle - interior flocked to reduce light reflection - "
          + "fits 6.25\" square gel frame slot")
      .price(new BigDecimal("21.21"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("TOP_HAT_FULL_WHITE")
      .name("Top hat full, white")
      .manufacturer("City Theatrical")
      .description("White full length top hat - controls light spill and "
          + "reduces lens viewing angle - interior flocked to reduce light reflection - "
          + "fits 6.25\" square gel frame slot")
      .price(new BigDecimal("21.21"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("TOP_HAT_HALF_BLACK")
      .name("Top hat half, black")
      .manufacturer("City Theatrical")
      .description("Black half length top hat - controls light spill and "
          + "reduces lens viewing angle - interior flocked to reduce light reflection - "
          + "fits 6.25\" square gel frame slot")
      .price(new BigDecimal("20.20"))
      .build());
  
  list.add(Accessory.builder()
      .accessoryId("TOP_HAT_HALF_WHITE")
      .name("Top hat half, white")
      .manufacturer("City Theatrical")
      .description("White half length top hat - controls light spill "
          + "and reduces lens viewing angle - interior flocked to reduce light reflection - "
          + "fits 6.25\" square gel frame slot")
      .price(new BigDecimal("21.21"))
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
