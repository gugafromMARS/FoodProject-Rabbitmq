package gsc.projects.orderservice.converter;


import gsc.projects.orderservice.dto.OrderCreateDto;
import gsc.projects.orderservice.dto.OrderDto;
import gsc.projects.orderservice.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    public OrderDto toDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .uuidOrder(order.getUuidOrder())
                .restaurantEmail(order.getRestaurantEmail())
                .userEmail(order.getUserEmail())
                .foodAndQuantity(order.getFoodAndQuantity())
                .build();
    }


    public Order fromCreateDto(OrderCreateDto orderCreateDto){
        return Order.build()
                .withUUID(orderCreateDto.getUuidOrder())
                .withRestaurantEmail(orderCreateDto.getRestaurantEmail())
                .withUserEmail(orderCreateDto.getUserEmail())
                .withFoodAndQuantity(orderCreateDto.getFoodAndQuantity())
                .build();
    }
}
