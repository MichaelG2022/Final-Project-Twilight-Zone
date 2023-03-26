package cds.twilight.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import cds.twilight.entity.Accessory;
import lombok.extern.slf4j.Slf4j;

/*
 * Component annotation tells Spring to pay attention to this class.
 * 
 * Slf4j annotation enables Lombok logging. Log entry is made at debug level showing the status of
 * the request being made.
 */
@Component
@Slf4j
public class DefaultTwilightAccessoryDao implements TwilightAccessoryDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Accessory> fetchAccessories() {
    log.debug("DAO: Accessory list request");
    // @formatter:off   
      
      String sql = ""
          + "SELECT * FROM accessories ";

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
            public Accessory mapRow(ResultSet rs, int rowNum) throws SQLException {
              return Accessory.builder()
                  .accessoryPK(rs.getInt("accessory_pk"))
                  .accessoryId(rs.getString("accessory_id"))
                  .name(rs.getString("name"))
                  .manufacturer(rs.getString("manufacturer"))
                  .description(rs.getString("description"))
                  .price(rs.getBigDecimal("price"))
                  .build();
            } // end mapRow
          } // end RowMapper
      ); // end jdbcTemplate.query    
  } // end fetchFixtures
} // end CLASS
