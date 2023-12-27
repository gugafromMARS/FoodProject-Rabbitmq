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
    private String userAddress;
    private String restaurantEmail;
    private String userEmail;

    @Override
    public String toString() {
        return "ShippingEvent{" +
                "uuidOrder=" + uuidOrder +
                ", trackingNumber=" + trackingNumber +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", restaurantEmail='" + restaurantEmail + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
