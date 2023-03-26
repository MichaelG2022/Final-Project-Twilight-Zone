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
public class Lamp implements Comparable<Lamp> {
  private int lampPK;
  private String lampId;
  private int wattage;
  private String description;
  private BigDecimal price;  
  
  @JsonIgnore
  public int getLampPK() {
    return lampPK;
  }

  @Override
  public int compareTo(Lamp that) {
 // @formatter:off
    return Comparator
          .comparing(Lamp::getLampId)
          .thenComparing(Lamp::getWattage)
          .compare(this, that);
      // @formatter:on
  } // end compareTo

} // end CLASS
