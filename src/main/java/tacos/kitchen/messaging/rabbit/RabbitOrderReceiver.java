package tacos.kitchen.messaging.rabbit;

import java.util.Optional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
public class RabbitOrderReceiver {

  private RabbitTemplate rabbit;

  @Autowired
  public RabbitOrderReceiver(RabbitTemplate rabbit) {
    this.rabbit = rabbit;
  }

  public Optional<Order> receiveOrder() {
    return Optional.ofNullable(
        rabbit.receiveAndConvert("tacocloud.orders", new ParameterizedTypeReference<Order>() {}));
  }
}
