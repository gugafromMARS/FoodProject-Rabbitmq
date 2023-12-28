package gsc.projects.shipppingservice.model;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
