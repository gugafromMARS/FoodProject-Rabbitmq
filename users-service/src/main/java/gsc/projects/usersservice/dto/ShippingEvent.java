package gsc.projects.usersservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class ShippingEvent {

    private UUID uuidOrder;
    private UUID trackingNumber;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String restaurantName;
    private String restaurantEmail;

    @Override
    public String toString() {
        return "ShippingEvent{" +
                "uuidOrder=" + uuidOrder +
                ", trackingNumber=" + trackingNumber +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantEmail='" + restaurantEmail + '\'' +
                '}';
    }
}
