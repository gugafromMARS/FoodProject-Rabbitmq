package gsc.projects.shipppingservice.dto;


import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter

public class OrderDto {

    private Long id;

    private UUID uuidOrder;

    private String restaurantEmail;

    private String userEmail;

    private Map<String, Double> foodAndQuantity;

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", uuidOrder=" + uuidOrder +
                ", restaurantEmail='" + restaurantEmail + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", foodAndQuantity=" + foodAndQuantity +
                '}';
    }
}
