package gsc.projects.restaurantservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuCreateDto {

    List<FoodCreateDto> foodCreateDtoList;
}
