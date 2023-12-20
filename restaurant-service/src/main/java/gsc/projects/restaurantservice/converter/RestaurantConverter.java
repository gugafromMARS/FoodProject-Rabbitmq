package gsc.projects.restaurantservice.converter;


import gsc.projects.restaurantservice.dto.RestaurantCreateDto;
import gsc.projects.restaurantservice.dto.RestaurantDto;
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
                .build();
    }

}
