package gsc.projects.orderservice.converter;


import gsc.projects.orderservice.dto.OrderCreateDto;
import gsc.projects.orderservice.dto.OrderDto;
import gsc.projects.orderservice.dto.OrderEvent;
import gsc.projects.orderservice.dto.UserDto;
import gsc.projects.orderservice.model.Order;
import gsc.projects.orderservice.service.APIClient;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    private APIClient apiClient;

    public OrderConverter(APIClient apiClient) {
        this.apiClient = apiClient;
    }

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
        return Order.builder()
                .withUUID(orderCreateDto.getUuidOrder())
                .withRestaurantEmail(orderCreateDto.getRestaurantEmail())
                .withUserEmail(orderCreateDto.getUserEmail())
                .withFoodAndQuantity(orderCreateDto.getFoodAndQuantity())
                .build();
    }

    public OrderEvent toEvent(Order order){
        UserDto userDto = apiClient.getUser(order.getUserEmail());
        return OrderEvent.builder()
                .uuidOrder(order.getUuidOrder())
                .restaurantEmail(order.getRestaurantEmail())
                .userName(userDto.getName())
                .userAddress(userDto.getAddress())
                .userEmail(userDto.getEmail())
                .foodAndQuantity(order.getFoodAndQuantity())
                .build();
    }



}
