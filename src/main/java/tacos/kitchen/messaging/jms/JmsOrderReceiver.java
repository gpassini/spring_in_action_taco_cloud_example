package tacos.kitchen.messaging.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import tacos.Order;

@Component
public class JmsOrderReceiver implements OrderReceiver {

	private JmsTemplate jms;

	@Autowired
	public JmsOrderReceiver(JmsTemplate jms) {
		this.jms = jms;
	}

	@Override
	public Order receiveOrder() throws JMSException {
		return (Order) jms.receiveAndConvert();
	}

}
