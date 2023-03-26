package cds.twilight.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import lombok.Getter;

public class CreateOrderTestSupport {

  @LocalServerPort
  private int serverPort;

  @Autowired
  @Getter
  private TestRestTemplate restTemplate;

  protected String getBaseUriForOrders() {
    return String.format("http://localhost:%d/orders", serverPort);
  } // end getBaseUriForOrders

  protected String createLightOrderBody() {
  // @formatter:off
  return "{\n"
      + "  \"customer\":\"YOUNG_SUE\",\n"
      + "  \"fixture\":\"ETC_S4_SR_BLACK_10_90_DEG_EDISON\",\n"
      + "  \"lens\":\"ETC_S4_SR_BLACK_36_DEG\",\n"
      + "  \"lamp\":\"HPL_LAMP_750W_120V_2000H\",\n"
      + "  \"accessories\":[\n"
      + "    \"SAFETY_CABLE_30_INCH_BLACK\",\n"
      + "    \"DROP_IN_IRIS_SOURCE_4\",\n"
      + "    \"GOBO_HOLDER_A_SIZE_FOR_S4\",\n"
      + "    \"TOP_HAT_FULL_BLACK\"\n"
      + "  ]\n"
      + "}";
  // @formatter:on      

  } // end createLightOrderBody

} // end CLASS

/*
{"customer": "YOUNG_SUE",
 "fixture": "ETC_S4_SR_BLACK_10_90_DEG_EDISON",
 "lens": "ETC_S4_SR_BLACK_50_DEG",
 "lamp": "HPL_LAMP_750W_120V_2000H",
 "accessories": [
   "SAFETY_CABLE_30_INCH_BLACK",
   "DROP_IN_IRIS_SOURCE_4",
   "GOBO_HOLDER_A_SIZE_FOR_S4",
   "TOP_HAT_FULL_BLACK"]}
   */
