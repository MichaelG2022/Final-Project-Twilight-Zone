package cds.twilight.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import cds.twilight.controller.support.FetchLampTestSupport;
import cds.twilight.entity.Lamp;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
// @formatter:off
@Sql(
    scripts = {"classpath:flyway/migrations/twilight_schema.sql",
               "classpath:flyway/migrations/twilight_data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
public class FetchLampTest extends FetchLampTestSupport {
  
//********************************************************************************************
//VALID LAMP WATTAGE TEST - successful/OK - 200 status code
//********************************************************************************************
 @Test
 void testThatLampsAreReturnedWhenAValidWattageIsSupplied() {
   // Given: a valid model, trim, and URI
   int wattage = 575;
   String uri = String.format("%s?wattage=%d", getBaseUriForLamps(), wattage);


   // When: a connection is made to the URI
   ResponseEntity<List<Lamp>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null,
       new ParameterizedTypeReference<>() {});

   // Then: a success (OK - 200) status code is returned
   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

   List<Lamp> actual = response.getBody();

   List<Lamp> expected = buildExpected();

   assertThat(actual).isEqualTo(expected);

 } // end testThatLampsAreReturnedWhenAValidWattageIsSupplied

} // end CLASS
