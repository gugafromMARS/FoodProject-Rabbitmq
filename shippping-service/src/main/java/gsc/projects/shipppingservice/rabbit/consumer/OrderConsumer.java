package gsc.projects.shipppingservice.rabbit.consumer;

import gsc.projects.shipppingservice.dto.OrderDto;
import gsc.projects.shipppingservice.service.ShippingServiceImp;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private final ShippingServiceImp shippingServiceImp;

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void consume(OrderDto orderDto){
        LOGGER.info(String.format("Order received in Shipping Service -> %s", orderDto.toString()));
        shippingServiceImp.sendOrderToUser(orderDto);
    }
}
