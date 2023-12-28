package gsc.projects.shipppingservice.service;


import gsc.projects.shipppingservice.converter.ShippingConverter;
import gsc.projects.shipppingservice.dto.OrderEventDto;
import gsc.projects.shipppingservice.model.ShippingEvent;
import gsc.projects.shipppingservice.rabbit.producer.ShippingProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingServiceImp {

    private final ShippingProducer shippingProducer;
    private final ShippingConverter shippingConverter;

    public void sendOrderToUser(OrderEventDto orderEventDto){
        ShippingEvent shippingEvent = shippingConverter.fromOrder(orderEventDto);
        shippingProducer.send(shippingEvent);
    }

}
