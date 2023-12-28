package gsc.projects.restaurantservice.service;


import gsc.projects.restaurantservice.converter.RestaurantConverter;
import gsc.projects.restaurantservice.dto.*;
import gsc.projects.restaurantservice.model.Order;
import gsc.projects.restaurantservice.model.Restaurant;
import gsc.projects.restaurantservice.repository.RestaurantRepository;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RestaurantServiceImp {

    private final RestaurantConverter restaurantConverter;
    private final RestaurantRepository restaurantRepository;


    public Map<String, Double> addOnMenu(Long id, RestaurantUpdateAdd restaurantUpdateAdd) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
        for(Map.Entry<String, Double> entry : restaurantUpdateAdd.getMenu().entrySet()){
            existingRestaurant.getMenu().put(entry.getKey().toUpperCase().trim(), entry.getValue());
        }
        restaurantRepository.save(existingRestaurant);
        return existingRestaurant.getMenu();
    }


    public RestaurantDto createRestaurant(RestaurantCreateDto restaurantCreateDto) {
        Restaurant existingRestaurant = restaurantRepository.findByRestaurantEmail(restaurantCreateDto.getRestaurantEmail());
        if(existingRestaurant != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant already exists");
        }
        Restaurant newRestaurant = restaurantConverter.fromCreateDto(restaurantCreateDto);
        restaurantRepository.save(newRestaurant);
        return restaurantConverter.toDto(newRestaurant);
    }


    public RestaurantDto getByName(String name) {
        Restaurant existingRestaurant = restaurantRepository.findByName(name.toUpperCase().trim());
        if(existingRestaurant == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This restaurant doesn't exists or wrong name");
        }
        return restaurantConverter.toDto(existingRestaurant);
    }

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> restaurantConverter.toDto(restaurant))
                .toList();
    }

    public void deleteById(Long id) {
        restaurantRepository.delete(restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found")));
    }

    public RestaurantDto updateRestaurantEmail(Long id, RestaurantUpdateEmail restaurantUpdateEmail) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
        existingRestaurant.setRestaurantEmail(restaurantUpdateEmail.getEmail());
        restaurantRepository.save(existingRestaurant);
        return restaurantConverter.toDto(existingRestaurant);
    }

    public Map<String, Double> removeOnMenu(Long id, RestaurantUpdateRemove restaurantUpdateRemove) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
        for(String s : restaurantUpdateRemove.getMenu()){
            if(existingRestaurant.getMenu().containsKey(s)){
                existingRestaurant.getMenu().remove(s);
            }
        }
        restaurantRepository.save(existingRestaurant);
        return existingRestaurant.getMenu();
    }

    public void addOrder(OrderEvent orderEvent) {
        Restaurant restaurant = restaurantRepository.findByRestaurantEmail(orderEvent.getRestaurantEmail());
        if(restaurant == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        Order order = restaurantConverter.fromOrderEvent(orderEvent, restaurant);
        restaurant.getOrderList().add(order);
        restaurantRepository.save(restaurant);
    }

    public List<Order> getAllOrders(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"))
                .getOrderList();
    }

    public void updateOrdersList(Long id, UUID orderUUID) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
        restaurant.setOrderList(restaurant.getOrderList().stream().filter(order -> order.getUuidOrder() != orderUUID).toList());
        restaurantRepository.save(restaurant);
    }
}
