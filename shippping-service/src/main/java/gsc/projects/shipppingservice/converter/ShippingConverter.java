package gsc.projects.shipppingservice.converter;


import gsc.projects.shipppingservice.dto.OrderEventDto;
import gsc.projects.shipppingservice.model.ShippingEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShippingConverter {

    public ShippingEvent fromOrder(OrderEventDto orderEventDto){
        return ShippingEvent.builder()
                .uuidOrder(orderEventDto.getUuidOrder())
                .trackingNumber(UUID.randomUUID())
                .userName(orderEventDto.getUserName())
                .userAddress(orderEventDto.getUserAddress())
                .userEmail(orderEventDto.getUserEmail())
                .restaurantName(orderEventDto.getRestaurantName())
                .restaurantEmail(orderEventDto.getRestaurantEmail())
                .build();
    }
}
