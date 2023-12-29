package gsc.projects.orderservice.service;


import gsc.projects.orderservice.converter.OrderConverter;
import gsc.projects.orderservice.dto.OrderCreateDto;
import gsc.projects.orderservice.dto.OrderDto;
import gsc.projects.orderservice.model.Order;
import gsc.projects.orderservice.rabbitmq.producer.OrderProducer;
import gsc.projects.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImp {

    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderDto createOrder(OrderCreateDto orderCreateDto) {
       Order newOrder = orderConverter.fromCreateDto(orderCreateDto);
       orderRepository.save(newOrder);
       OrderDto orderDto = orderConverter.toDto(newOrder);
       orderProducer.sendOrder(orderConverter.toEvent(newOrder));
       return orderDto;
    }

    public OrderDto getOrderByUuid(UUID uuid) {
        Order existingOrder = orderRepository.findByUuidOrder(uuid);
        if(existingOrder == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        return orderConverter.toDto(existingOrder);
    }

    public List<OrderDto> getAllOrders(String userEmail) {
        return orderRepository.findByUserEmail(userEmail).stream()
                .map(order -> orderConverter.toDto(order))
                .toList();
    }
}
