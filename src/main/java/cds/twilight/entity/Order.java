package cds.twilight.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private int orderPK;
  private Customer customer;
  private Fixture fixture;
  private Lens lens;
  private Lamp lamp;
  private LocalDateTime orderDate;
  private List<Accessory> accessories;
  private BigDecimal totalPrice;
  
  @JsonIgnore
  public int getOrderPK() {
    return orderPK;
  } // end getOrderPK 

} // end CLASS
