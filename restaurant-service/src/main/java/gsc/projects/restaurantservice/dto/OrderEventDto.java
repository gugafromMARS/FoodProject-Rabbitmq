package gsc.projects.restaurantservice.dto;

import jakarta.persistence.ElementCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@Builder
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
        return "OrderEventDto{" +
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
