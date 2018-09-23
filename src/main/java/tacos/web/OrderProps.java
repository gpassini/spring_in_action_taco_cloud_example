package tacos.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "tacos.orders")
@Data
@Validated
public class OrderProps {

  @Min(value = 5, message = "must be between 5 and 25")
  @Max(value = 25, message = "muste be between 5 and 25")
  private int pageSize = 20;
}
