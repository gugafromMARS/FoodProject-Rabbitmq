package gsc.projects.usersservice.rabbitmq.consumer;


import gsc.projects.usersservice.dto.ShippingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingConsumer.class);


    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void consume(ShippingEvent shippingEvent){
        LOGGER.info(String.format("ShippingEvent received in User Service -> %s", shippingEvent.toString()));
    }
}
