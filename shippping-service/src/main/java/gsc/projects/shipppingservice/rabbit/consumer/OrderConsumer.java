package gsc.projects.shipppingservice.rabbit.consumer;

import gsc.projects.shipppingservice.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    public void consume(OrderDto orderDto){
        LOGGER.info(String.format("Order received in Shipping Service -> %s", orderDto.toString()));
    }
}
