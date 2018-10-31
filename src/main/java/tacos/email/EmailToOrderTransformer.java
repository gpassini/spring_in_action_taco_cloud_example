package tacos.email;

import javax.mail.Message;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<Order> {

  @Override
  protected AbstractIntegrationMessageBuilder<Order> doTransform(Message mailMessage)
      throws Exception {
    Order tacoOrder = processPayload(mailMessage);
    return MessageBuilder.withPayload(tacoOrder);
  }

  private Order processPayload(Message mailMessage) {
    return new Order("foo@bar.com");
  }
}
