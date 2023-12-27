package gsc.projects.shipppingservice.rabbit.producer;


import gsc.projects.shipppingservice.model.ShippingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShippingProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingProducer.class);
    private final RabbitTemplate rabbitTemplate;

    public ShippingProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;



    public void send(ShippingEvent shippingEvent){
        LOGGER.info(String.format("Shipping sent to User Service -> %s", shippingEvent.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, shippingEvent);
    }
}
