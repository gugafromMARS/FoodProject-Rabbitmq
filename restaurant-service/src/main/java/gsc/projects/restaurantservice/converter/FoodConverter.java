package gsc.projects.restaurantservice.converter;


import gsc.projects.restaurantservice.dto.FoodCreateDto;
import gsc.projects.restaurantservice.model.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodConverter {


    public Food fromCreateDto(FoodCreateDto foodCreateDto){
        return Food.builder()
                .withName(foodCreateDto.getName())
                .withPrice(foodCreateDto.getPrice())
                .build();
    }
}
