package tacos.messaging;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import tacos.Order;

public class RabbitOrderMessagingService implements OrderMessagingService {

	private RabbitTemplate rabbit;

	@Autowired
	public RabbitOrderMessagingService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}

	@Override
	public void sendOrder(Order order) {
		rabbit.convertAndSend("tacocloud.order", order, message -> {
			MessageProperties properties = message.getMessageProperties();
			properties.setHeader("X_ORDER_SOURCE", "WEB");
			return message;
		});
	}

}
