package cds.twilight.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fixture implements Comparable<Fixture>{
  private int fixturePK;
  private String fixtureId;
  private String color;
  private String plugType;
  private String description;
  private BigDecimal price;
    
  @JsonIgnore
  public int getFixturePK() {
    return fixturePK;
  }

  @Override
  public int compareTo(Fixture that) {
    // @formatter:off
    return Comparator
          .comparing(Fixture::getFixtureId)
          .thenComparing(Fixture::getColor)
          .thenComparing(Fixture::getPlugType)
          .compare(this, that);
      // @formatter:on
  } // end compareTo

} // end CLASS
