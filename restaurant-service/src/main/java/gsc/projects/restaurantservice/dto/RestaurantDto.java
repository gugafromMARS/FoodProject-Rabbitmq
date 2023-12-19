package gsc.projects.restaurantservice.dto;

import gsc.projects.restaurantservice.model.RestaurantMenu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class RestaurantDto {

    private Long id;
    private String name;
    private String restaurantEmail;
    private String address;
    private RestaurantMenu restaurantMenu;
}
