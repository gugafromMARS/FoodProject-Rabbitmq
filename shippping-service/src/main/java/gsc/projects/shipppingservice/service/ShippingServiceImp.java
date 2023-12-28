package gsc.projects.shipppingservice.service;


import gsc.projects.shipppingservice.converter.ShippingConverter;
import gsc.projects.shipppingservice.dto.OrderDto;
import gsc.projects.shipppingservice.dto.UserDto;
import gsc.projects.shipppingservice.model.ShippingEvent;
import gsc.projects.shipppingservice.rabbit.producer.ShippingProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingServiceImp {

    private final APIClient apiClient;
    private final ShippingProducer shippingProducer;
    private final ShippingConverter shippingConverter;

    public void sendOrderToUser(OrderDto orderDto){
        UserDto user = getUserAddress(orderDto.getUserEmail());
        ShippingEvent shippingEvent = shippingConverter.fromOrder(orderDto, user);
        shippingProducer.send(shippingEvent);
    }

    public UserDto getUserAddress(String userEmail){
        return apiClient.getUser(userEmail);
    }
}
