package gsc.projects.restaurantservice.converter;


import gsc.projects.restaurantservice.dto.*;
import gsc.projects.restaurantservice.model.Order;
import gsc.projects.restaurantservice.model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RestaurantConverter {


    public RestaurantDto toDto(Restaurant restaurant){
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .restaurantEmail(restaurant.getRestaurantEmail())
                .address(restaurant.getAddress())
                .menu(restaurant.getMenu())
                .build();
    }

    public Restaurant fromCreateDto(RestaurantCreateDto restaurantCreateDto){
        Map<String, Double> foods = restaurantCreateDto.getMenu().entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toUpperCase().trim(), entry -> entry.getValue()));

        return Restaurant.builder()
                .withName(restaurantCreateDto.getName().toUpperCase().trim())
                .withEmail(restaurantCreateDto.getRestaurantEmail())
                .withAddress(restaurantCreateDto.getAddress())
                .withMenu(foods)
                .withOrderList()
                .build();
    }

    public Order fromOrderEvent(OrderEvent orderEvent, Restaurant restaurant){
        return Order.builder()
                .uuidOrder(orderEvent.getUuidOrder())
                .userName(orderEvent.getUserName())
                .userAddress(orderEvent.getUserAddress())
                .userEmail(orderEvent.getUserEmail())
                .foodAndQuantity(orderEvent.getFoodAndQuantity())
                .restaurant(restaurant)
                .build();
    }

    public OrderEventDto fromOrder(Order order){
        return OrderEventDto.builder()
                .uuidOrder(order.getUuidOrder())
                .restaurantName(order.getRestaurant().getName())
                .restaurantEmail(order.getRestaurant().getRestaurantEmail())
                .userName(order.getUserName())
                .userAddress(order.getUserAddress())
                .userEmail(order.getUserEmail())
                .foodAndQuantity(order.getFoodAndQuantity())
                .build();
    }

    public OrderDto fromOrderToDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .uuidOrder(order.getUuidOrder())
                .foodAndQuantity(order.getFoodAndQuantity())
                .build();
    }

}
