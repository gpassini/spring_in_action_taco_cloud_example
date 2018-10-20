package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import tacos.Order;

public class JmsOrderMessagingService implements OrderMessagingService {

	@Autowired
	JmsTemplate jms;

	@Override
	public void sendOrder(Order order) {
		jms.convertAndSend(order, (message) -> {
			message.setStringProperty("X_ORDER_SOURCE", "WEB");
			return message;
		});
	}

}
