package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tacos.Order;

@Component
public class OrderReceiver {

	private RabbitTemplate rabbit;
	private MessageConverter converter;

	@Autowired
	public OrderReceiver(RabbitTemplate rabbit, MessageConverter converter) {
		this.rabbit = rabbit;
		this.converter = converter;
	}

	public Order receiveOrder() {
		Message message = rabbit.receive("tacocloud.orders");
		return message != null ? (Order) converter.fromMessage(message) : null;
	}
}
