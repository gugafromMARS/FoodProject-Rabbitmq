package gsc.projects.orderservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class OrderCreateDto {

    private UUID uuidOrder;
    private String restaurantEmail;
    private String userEmail;
    private Map<String, Double> foodAndQuantity;
}
