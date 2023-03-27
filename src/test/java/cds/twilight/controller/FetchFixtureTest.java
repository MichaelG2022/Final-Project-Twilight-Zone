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
import cds.twilight.controller.support.FetchFixtureTestSupport;
import cds.twilight.entity.Fixture;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
// @formatter:off
@Sql(
    scripts = {"classpath:flyway/migrations/twilight_schema.sql",
               "classpath:flyway/migrations/twilight_data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
public class FetchFixtureTest extends FetchFixtureTestSupport {
  
//********************************************************************************************
// FIXTURE LIST TEST - successful/OK - 200 status code
// ********************************************************************************************
  @Test
  void testThatFixtureListIsReturnedWhenARequestIsMade() {
    // Given: a valid color and URI
    String color = "black";
    String uri = String.format("%s?color=%s", getBaseUriForFixtures(), color);


    // When: a connection is made to the URI
    ResponseEntity<List<Fixture>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null,
        new ParameterizedTypeReference<>() {});

    // Then: a success (OK - 200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    List<Fixture> actual = response.getBody();

    List<Fixture> expected = buildExpected();

    assertThat(actual).isEqualTo(expected);

  } // end testThatFixtureListIsReturnedWhenARequestIsMade

} // end CLASS
