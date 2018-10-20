package tacos.messaging;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.Order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@EnableJms
@Configuration
public class JmsConfig {

	@Bean
	public MappingJackson2MessageConverter messageConverter() {
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTypeIdPropertyName("_typeId");
		Map<String, Class<?>> typeIdMappings = new HashMap<>();
		typeIdMappings.put("order", Order.class);
		messageConverter.setTypeIdMappings(typeIdMappings);
		return messageConverter;
	}
}
