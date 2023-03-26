package cds.twilight.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import cds.twilight.entity.Accessory;
import cds.twilight.entity.Customer;
import cds.twilight.entity.Fixture;
import cds.twilight.entity.Lamp;
import cds.twilight.entity.Lens;
import cds.twilight.entity.Order;

public interface TwilightOrderDao {

  Order saveOrder(Customer customer, Fixture fixture, Lens lens, Lamp lamp, BigDecimal price,
      List<Accessory> accessories);

  List<Accessory> fetchAccessories(
      List<@NotNull @Length(max = 50)  @Pattern(regexp = "[A-z0-9_\\s]*") String> accessories);

  Optional<Customer> fetchCustomer(String customer);

  Optional<Fixture> fetchFixture(String fixture);

  Optional<Lens> fetchLens(String lens);

  Optional<Lamp> fetchLamp(String lamp);

}
