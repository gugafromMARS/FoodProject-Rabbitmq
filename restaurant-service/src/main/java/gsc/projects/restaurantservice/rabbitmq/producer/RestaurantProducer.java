package gsc.projects.restaurantservice.rabbitmq.producer;


import gsc.projects.restaurantservice.dto.OrderEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestaurantProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantProducer.class);
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public RestaurantProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderToShipping(OrderEventDto orderEventDto){
        LOGGER.info(String.format("%s, sent order to shipping service -> %s", orderEventDto.getRestaurantName(), orderEventDto.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, orderEventDto);
    }

}
