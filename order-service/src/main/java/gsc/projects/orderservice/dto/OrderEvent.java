package gsc.projects.orderservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderEvent {

    private UUID uuidOrder;

    private String restaurantEmail;

    private String userName;

    private String userAddress;

    private String userEmail;

    private Map<String, Double> foodAndQuantity;
}
