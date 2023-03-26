package cds.twilight.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import cds.twilight.entity.Fixture;
import lombok.extern.slf4j.Slf4j;

/*
 * Component annotation tells Spring to pay attention to this class.
 * 
 * Slf4j annotation enables Lombok logging. Log entry is made at debug level showing the status of
 * the request being made.
 */
@Component
@Slf4j
public class DefaultTwilightFixtureDao implements TwilightFixtureDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Fixture> fetchFixtures(String color) {
    log.debug("DAO: Fixture list request: color={}", color);
    // @formatter:off

    // Returns all data or specified parameters.
    if(color.equalsIgnoreCase("allall")) {
      
      String sql = ""
          + "SELECT * FROM fixtures "; 

      /*
       * RowMapper works with just diamond operator because fetchJeeps return
       * statement implies List of Jeeps.
       * 
       * RowMapper calls mapRow to loop through the result set and return an
       * object for each row in the result set.
       * 
       * mapRow is an unimplemented method for RowMapper that returns an object
       * for every row in the result set.
       * 
       * The Builder allows us to get values from the result set for all fields for
       * each object returned by mapRow.
       * 
       * Use column names instead of column numbers because column order might be
       * changed. It is recommended to use columns in order from left to right.
       */
      return jdbcTemplate.query(sql,
          new RowMapper<>() {

            @Override
            public Fixture mapRow(ResultSet rs, int rowNum) throws SQLException {
              return Fixture.builder()
                  .fixturePK(rs.getInt("fixture_pk"))
                  .fixtureId(rs.getString("fixture_id"))
                  .color(rs.getString("color"))
                  .plugType(rs.getString("plug_type"))
                  .description(rs.getString("description"))
                  .price(rs.getBigDecimal("price"))
                  .build();
            } // end mapRow
          } // end RowMapper
      ); // end jdbcTemplate.query

    } else {

      String sql = ""
          + "SELECT * FROM fixtures "
          + "WHERE color = :color";

      Map<String, Object> params = new HashMap<>();
      params.put("color", color);

      return jdbcTemplate.query(sql, params,
          new RowMapper<>() {

            @Override
            public Fixture mapRow(ResultSet rs, int rowNum) throws SQLException {
              return Fixture.builder()
                  .fixturePK(rs.getInt("fixture_pk"))
                  .fixtureId(rs.getString("fixture_id"))
                  .color(rs.getString("color"))
                  .plugType(rs.getString("plug_type"))
                  .description(rs.getString("description"))
                  .price(rs.getBigDecimal("price"))
                  .build();
              // @formatter:on
            } // end mapRow
          } // end RowMapper
      ); // end jdbcTemplate.query
    } // end IF-ELSE
  } // end fetchFixtures
} // end CLASS
