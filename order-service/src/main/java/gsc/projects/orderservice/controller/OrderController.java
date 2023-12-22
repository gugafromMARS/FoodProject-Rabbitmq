package gsc.projects.orderservice.controller;


import gsc.projects.orderservice.dto.OrderCreateDto;
import gsc.projects.orderservice.service.OrderServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderServiceImp orderServiceImp;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderCreateDto orderCreateDto){
        orderCreateDto.setUuidOrder(UUID.randomUUID());
        return new ResponseEntity<>(orderServiceImp.createOrder(orderCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/uuid/{uuidOrder}")
    public ResponseEntity<?> getOrder(@PathVariable ("uuidOrder") UUID uuid){
        return ResponseEntity.ok(orderServiceImp.getOrderByUuid(uuid));
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getOrders(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(orderServiceImp.getAllOrders(userEmail));
    }
}
