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
public class Lens implements Comparable<Lens> {
  private int lensPK;
  private String lensId;
  private int lensSize;
  private String lensColor;
  private String description;
  private BigDecimal price;
  
  @JsonIgnore
  public int getLensPK() {
    return lensPK;
  } // end getLensPK 

  @Override
  public int compareTo(Lens that) {
 // @formatter:off
    return Comparator
          .comparing(Lens::getLensId)
          .thenComparing(Lens::getLensSize)
          .thenComparing(Lens::getLensColor)
          .compare(this, that);
      // @formatter:on
  } // end compareTo

} // end CLASS
