package gsc.projects.shipppingservice.converter;


import gsc.projects.shipppingservice.dto.OrderDto;
import gsc.projects.shipppingservice.dto.UserDto;
import gsc.projects.shipppingservice.model.ShippingEvent;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class ShippingConverter {

    public ShippingEvent fromOrder(OrderDto orderDto, UserDto userDto){
        return ShippingEvent.builder()
                .uuidOrder(orderDto.getUuidOrder())
                .trackingNumber(UUID.randomUUID())
                .userName(userDto.getName())
                .userAddress(userDto.getAddress())
                .userEmail(userDto.getEmail())
                .restaurantEmail(orderDto.getRestaurantEmail())
                .build();
    }
}
