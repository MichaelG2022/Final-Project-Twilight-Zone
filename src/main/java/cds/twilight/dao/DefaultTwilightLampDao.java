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
import cds.twilight.entity.Lamp;
import lombok.extern.slf4j.Slf4j;

/*
 * Component annotation tells Spring to pay attention to this class.
 * 
 * Slf4j annotation enables Lombok logging. Log entry is made at debug level showing the status of
 * the request being made.
 */
@Component
@Slf4j
public class DefaultTwilightLampDao implements TwilightLampDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Lamp> fetchLamps(int wattage) {
    log.debug("DAO: Lamp list request: wattage={}", wattage);
    // @formatter:off          
    if(wattage == 9999) {
      
      String sql = ""
          + "SELECT * FROM lamps "; 

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
            public Lamp mapRow(ResultSet rs, int rowNum) throws SQLException {
              return Lamp.builder()
                  .lampPK(rs.getInt("lamp_pk"))
                  .lampId(rs.getString("lamp_id"))
                  .wattage(rs.getInt("wattage"))
                  .description(rs.getString("description"))
                  .price(rs.getBigDecimal("price"))
                  .build();
            } // end mapRow
          } // end RowMapper
      ); // end jdbcTemplate.query

    } else {

      String sql = ""
          + "SELECT * FROM lamps "
          + "WHERE wattage = :wattage";

      Map<String, Object> params = new HashMap<>();
      params.put("wattage", wattage);

      return jdbcTemplate.query(sql, params,
          new RowMapper<>() {

            @Override
            public Lamp mapRow(ResultSet rs, int rowNum) throws SQLException {
              return Lamp.builder()
                  .lampPK(rs.getInt("lamp_pk"))
                  .lampId(rs.getString("lamp_id"))
                  .wattage(rs.getInt("wattage"))
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
