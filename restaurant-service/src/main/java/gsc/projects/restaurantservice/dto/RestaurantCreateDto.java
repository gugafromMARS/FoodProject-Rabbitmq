package gsc.projects.restaurantservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RestaurantCreateDto {

    private String name;

    private String restaurantEmail;

    private String address;

    private Map<String, Double> menu;
}
