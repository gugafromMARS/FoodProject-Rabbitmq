package gsc.projects.restaurantservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@Builder
public class RestaurantDto {

    private Long id;
    private String name;
    private String restaurantEmail;
    private String address;
    private Map<String, Double> menu;
}
