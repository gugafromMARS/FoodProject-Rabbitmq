package gsc.projects.restaurantservice.rabbitmq.consumer;


import gsc.projects.restaurantservice.dto.OrderEvent;
import gsc.projects.restaurantservice.service.RestaurantServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private RestaurantServiceImp restaurantServiceImp;

    public OrderConsumer(RestaurantServiceImp restaurantServiceImp) {
        this.restaurantServiceImp = restaurantServiceImp;
    }

    @RabbitListener(queues = "${rabbitmq.consumer.queue.name}")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order Event received at Restaurant Service -> %s", orderEvent.toString()));
        restaurantServiceImp.addOrder(orderEvent);
    }

}
