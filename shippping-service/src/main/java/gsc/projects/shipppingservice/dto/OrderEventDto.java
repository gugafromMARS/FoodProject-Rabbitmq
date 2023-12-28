package gsc.projects.shipppingservice.dto;


import jakarta.persistence.ElementCollection;
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
public class OrderEventDto {

    private UUID uuidOrder;

    private String restaurantName;

    private String restaurantEmail;

    private String userName;

    private String userAddress;

    private String userEmail;

    @ElementCollection
    private Map<String, Double> foodAndQuantity;


    @Override
    public String toString() {
        return "OrderDto{" +
                "uuidOrder=" + uuidOrder +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantEmail='" + restaurantEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", foodAndQuantity=" + foodAndQuantity +
                '}';
    }
}
