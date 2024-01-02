package gsc.projects.orderservice.dto;


import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
