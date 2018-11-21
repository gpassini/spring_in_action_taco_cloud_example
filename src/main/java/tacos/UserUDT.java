package tacos;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import lombok.Data;

@Data
@UserDefinedType("user")
public class UserUDT {

  private final String username;
  private final String fullname;
  private final String phoneNumber;
}
