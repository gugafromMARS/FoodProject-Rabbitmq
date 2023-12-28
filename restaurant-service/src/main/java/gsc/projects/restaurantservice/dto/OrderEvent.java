package gsc.projects.restaurantservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {


    private UUID uuidOrder;

    private String restaurantEmail;

    private String userName;

    private String userAddress;

    private String userEmail;

    private Map<String, Double> foodAndQuantity;

    @Override
    public String toString() {
        return "OrderEvent{" +
                "uuidOrder=" + uuidOrder +
                ", restaurantEmail='" + restaurantEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", foodAndQuantity=" + foodAndQuantity +
                '}';
    }
}
