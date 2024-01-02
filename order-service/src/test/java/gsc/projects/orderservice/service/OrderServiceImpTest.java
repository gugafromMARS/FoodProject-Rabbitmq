package gsc.projects.orderservice.service;

import gsc.projects.orderservice.converter.OrderConverter;
import gsc.projects.orderservice.dto.OrderCreateDto;
import gsc.projects.orderservice.dto.OrderDto;
import gsc.projects.orderservice.model.Order;
import gsc.projects.orderservice.rabbitmq.producer.OrderProducer;
import gsc.projects.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderServiceImpTest {

    @Mock
    private OrderConverter orderConverter;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderProducer orderProducer;

    @Mock
    private APIClient apiClient;

    @InjectMocks
    private OrderServiceImp orderServiceImp;
    private OrderCreateDto orderCreateDto;
    private Order order;
    private OrderDto orderDto;

    @BeforeEach
    void setup(){
        orderRepository.deleteAll();

        orderCreateDto = new OrderCreateDto();
        orderCreateDto.setUserEmail("test@email.com");
        orderCreateDto.setRestaurantEmail("restaurant@email.com");

        order = new Order();
        order.setUserEmail(orderCreateDto.getUserEmail());
        order.setRestaurantEmail(orderCreateDto.getRestaurantEmail());

        orderDto = new OrderDto();
        orderDto.setUserEmail(order.getUserEmail());
        orderDto.setRestaurantEmail(order.getRestaurantEmail());
        orderDto.setUuidOrder(order.getUuidOrder());
    }

    @Nested
    @Tag("Order service unit tests")
    public class OrderUnitTests{

        @Test
        @DisplayName("Create a non exists order and return orderDto")
        public void createANonExistsOrder(){

            order.setUuidOrder(UUID.randomUUID());

            given(orderRepository.save(order)).willReturn(order);

            orderDto.setUuidOrder(order.getUuidOrder());

            when(orderRepository.findByUuidOrder(order.getUuidOrder())).thenReturn(order);

            assertEquals(orderDto.getUuidOrder().toString(), order.getUuidOrder().toString());

        }

        @Test
        @DisplayName("Get a exception for try to get an order with a non exists uuid")
        public void getAnExceptionForTryToGetOrderWithNonExistsUUID(){

            order.setUuidOrder(UUID.randomUUID());

            given(orderRepository.save(order)).willReturn(order);

            UUID uuid = UUID.randomUUID();

            when(orderRepository.findByUuidOrder(uuid)).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () -> {
                orderServiceImp.getOrderByUuid(uuid);
            });

        }
    }
}