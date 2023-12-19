package gsc.projects.restaurantservice.converter;


import gsc.projects.restaurantservice.dto.RestaurantCreateDto;
import gsc.projects.restaurantservice.dto.RestaurantDto;
import gsc.projects.restaurantservice.model.Food;
import gsc.projects.restaurantservice.model.Restaurant;
import gsc.projects.restaurantservice.model.RestaurantMenu;
import gsc.projects.restaurantservice.repository.FoodRepository;
import gsc.projects.restaurantservice.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RestaurantConverter {

    private MenuConverter menuConverter;
    private MenuRepository menuRepository;

    public RestaurantDto toDto(Restaurant restaurant){
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .restaurantEmail(restaurant.getRestaurantEmail())
                .address(restaurant.getAddress())
                .restaurantMenu(restaurant.getRestaurantMenu())
                .build();
    }

    public Restaurant fromCreateDto(RestaurantCreateDto restaurantCreateDto){
        RestaurantMenu restaurantMenu = menuConverter.fromCreateDto(restaurantCreateDto.getMenu());
        menuRepository.save(restaurantMenu);

        return Restaurant.builder()
                .withName(restaurantCreateDto.getName())
                .withEmail(restaurantCreateDto.getRestaurantEmail())
                .withAddress(restaurantCreateDto.getAddress())
                .withMenu(restaurantMenu)
                .build();
    }

}
