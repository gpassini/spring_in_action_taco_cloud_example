package tacos.kitchen.messaging.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
public class OrderListener {

  @RabbitListener(queues = "tacocloud.order.queue")
  public void receiveOrder(Order order) {

  }
}
