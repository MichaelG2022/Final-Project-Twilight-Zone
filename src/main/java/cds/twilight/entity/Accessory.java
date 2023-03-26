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
public class Accessory implements Comparable<Accessory> {
  private int accessoryPK;
  private String accessoryId;
  private String name;
  private String manufacturer;
  private String description;
  private BigDecimal price;

  @JsonIgnore
  public int getAccessoryPK() {
    return accessoryPK;
  }

  @Override
  public int compareTo(Accessory that) {
    // @formatter:off
    return Comparator
          .comparing(Accessory::getAccessoryId)
          .thenComparing(Accessory::getName)
          .compare(this, that);
      // @formatter:on
  } // end compareTo

} // end CLASS
