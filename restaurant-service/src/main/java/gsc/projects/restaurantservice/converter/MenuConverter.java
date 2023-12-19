package gsc.projects.restaurantservice.converter;


import gsc.projects.restaurantservice.dto.MenuCreateDto;
import gsc.projects.restaurantservice.model.Food;
import gsc.projects.restaurantservice.model.RestaurantMenu;
import gsc.projects.restaurantservice.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MenuConverter {

    private FoodConverter foodConverter;
    private FoodRepository foodRepository;

    public RestaurantMenu fromCreateDto(MenuCreateDto menuCreateDto){
        List<Food> foods = menuCreateDto.getFoodCreateDtoList().stream()
                .map(foodCreateDto ->  foodRepository.save(foodConverter.fromCreateDto(foodCreateDto)))
                .toList();

        return RestaurantMenu.builder()
                .withFoodList(foods)
                .build();
    }
}
