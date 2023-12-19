package gsc.projects.restaurantservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantCreateDto {

    private String name;

    private String restaurantEmail;

    private String address;

    private MenuCreateDto menu;
}
