package gsc.projects.restaurantservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RestaurantUpdateAdd {

    private Map<String, Double> menu;
}
