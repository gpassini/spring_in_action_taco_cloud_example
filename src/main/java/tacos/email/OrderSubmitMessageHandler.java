package tacos.email;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<Order> {

  private RestTemplate restTemplate;
  private ApiProperties apiProperties;

  @Autowired
  public OrderSubmitMessageHandler(RestTemplate restTemplate, ApiProperties apiProperties) {
    this.apiProperties = apiProperties;
    this.restTemplate = restTemplate;
  }

  @Override
  public Object handle(Order order, Map<String, Object> headers) {
    restTemplate.postForObject(apiProperties.getUrl(), order, String.class);
    return null;
  }

}
