package cds.twilight.entity;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
  
  @NotNull
  @Length(max = 50)
  @Pattern(regexp = "[A-z0-9_\\s]*")
  private String customer;
  
  @NotNull
  @Length(max = 50)
  @Pattern(regexp = "[A-z0-9_\\s]*")
  private String fixture;
  
  @NotNull
  @Length(max = 50)
  @Pattern(regexp = "[A-z0-9_\\s]*")
  private String lens;
  
  @NotNull
  @Length(max = 50)
  @Pattern(regexp = "[A-z0-9_\\s]*")
  private String lamp;
   
  private List<@NotNull @Length(max = 50) @Pattern(regexp = "[A-z0-9_\\s]*") String> accessories;

} // end CLASS
