package gsc.projects.orderservice.rabbitmq.producer;


import gsc.projects.orderservice.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(OrderDto orderDto){
        LOGGER.info(String.format("Order sent to Shipping Service -> %s", orderDto.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, orderDto);
    }

}
