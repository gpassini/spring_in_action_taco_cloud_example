package tacos.kitchen.messaging.jms;

import javax.jms.JMSException;

import tacos.Order;

public interface OrderReceiver {

	Order receiveOrder() throws JMSException;
}
