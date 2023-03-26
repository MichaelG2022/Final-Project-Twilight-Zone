package cds.twilight.controller.support;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import cds.twilight.entity.Fixture;
import lombok.Getter;

public class FetchFixtureTestSupport {
  
  @LocalServerPort
  private int serverPort;

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;

  protected String getBaseUriForFixtures() {
    return String.format("http://localhost:%d/fixtures", serverPort);
  } // end getBaseUriForFixtures

  protected List<Fixture> buildExpected() {
    List<Fixture> list = new LinkedList<>();
    
  // @formatter:off
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_10_90_DEG_NONE")
      .color("black")
      .plugType("none")
      .description("Black ETC Source Four Ellipsoidal Senior, Single clutch for 10-90 degree lenses,"
          + " Bare wire cable")
      .price(new BigDecimal("499.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_10_90_DEG_EDISON")
      .color("black")
      .plugType("NEMA 5-20P (Edison)")
      .description("Black ETC Source Four Ellipsoidal Senior, Single clutch for 10-90 degree lenses,"
          + " NEMA 5-20P (Edison) connector (installed)")
      .price(new BigDecimal("549.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_10_90_DEG_STAGE_PIN")
      .color("black")
      .plugType("GSP (Stage Pin)")
      .description("Black ETC Source Four Ellipsoidal Senior, Single clutch for 10-90 degree lenses,"
          + " GSP (Stage Pin) connector (installed)")
      .price(new BigDecimal("549.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_10_90_DEG_TWIST_LOCK")
      .color("black")
      .plugType("NEMA L5-20P (Twist Lock)")
      .description("Black ETC Source Four Ellipsoidal Senior, Single clutch for 10-90 degree lenses,"
          + " NEMA L5-20P (Twist Lock) connector (installed)")
      .price(new BigDecimal("549.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_10_90_DEG_DIMMER_DOUBLER_TWIST_LOCK")
      .color("black")
      .plugType("NEMA L5-15P (Dimmer Doubler Twist Lock)")
      .description("Black ETC Source Four Ellipsoidal Senior, Single clutch for 10-90 degree lenses,"
          + " NEMA L5-15P (Dimmer Doubler Twist Lock) connector (installed)")
      .price(new BigDecimal("549.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_5_DEG_NONE")
      .color("black")
      .plugType("none")
      .description("Black ETC Source Four Ellipsoidal Senior, Double clutch for 5 degree lenses,"
          + " Bare wire cable")
      .price(new BigDecimal("531.00"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_5_DEG_EDISON")
      .color("black")
      .plugType("NEMA 5-20P (Edison)")
      .description("Black ETC Source Four Ellipsoidal Senior, Double clutch for 5 degree lenses,"
          + " NEMA 5-20P (Edison) connector (installed)")
      .price(new BigDecimal("549.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_5_DEG_STAGE_PIN")
      .color("black")
      .plugType("GSP (Stage Pin)")
      .description("Black ETC Source Four Ellipsoidal Senior, Double clutch for 5 degree lenses,"
          + " GSP (Stage Pin) connector (installed)")
      .price(new BigDecimal("549.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_5_DEG_TWIST_LOCK")
      .color("black")
      .plugType("NEMA L5-20P (Twist Lock)")
      .description("Black ETC Source Four Ellipsoidal Senior, Double clutch for 5 degree lenses,"
          + " NEMA L5-20P (Twist Lock) connector (installed)")
      .price(new BigDecimal("549.50"))
      .build());
  
  list.add(Fixture.builder()
      .fixtureId("ETC_S4_SR_BLACK_5_DEG_DIMMER_DOUBLER_TWIST_LOCK")
      .color("black")
      .plugType("NEMA L5-15P (Dimmer Doubler Twist Lock)")
      .description("Black ETC Source Four Ellipsoidal Senior, Double clutch for 5 degree lenses,"
          + " NEMA L5-15P (Dimmer Doubler Twist Lock) connector (installed)")
      .price(new BigDecimal("549.50"))
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
