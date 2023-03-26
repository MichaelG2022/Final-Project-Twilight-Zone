package cds.twilight.entity;

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
public class Customer implements Comparable<Customer> {
  private int customerPK;
  private String customerId;
  private String firstName;
  private String lastName;
  private String phone;

  @JsonIgnore
  public int getCustomerPK() {
    return customerPK;
  }

  @Override
  public int compareTo(Customer that) {
    // @formatter:off
    return Comparator
          .comparing(Customer::getLastName)
          .thenComparing(Customer::getFirstName)
          .compare(this, that);
      // @formatter:on
  } // end compareTo

} // end CLASS
