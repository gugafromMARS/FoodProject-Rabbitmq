package gsc.projects.restaurantservice.service;


import gsc.projects.restaurantservice.converter.RestaurantConverter;
import gsc.projects.restaurantservice.dto.RestaurantCreateDto;
import gsc.projects.restaurantservice.dto.RestaurantDto;
import gsc.projects.restaurantservice.model.Food;
import gsc.projects.restaurantservice.model.Restaurant;
import gsc.projects.restaurantservice.model.RestaurantMenu;
import gsc.projects.restaurantservice.repository.FoodRepository;
import gsc.projects.restaurantservice.repository.MenuRepository;
import gsc.projects.restaurantservice.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImp {

    private final RestaurantConverter restaurantConverter;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final MenuRepository menuRepository;

    public RestaurantDto createRestaurant(RestaurantCreateDto restaurantCreateDto) {
        Restaurant existingRestaurant = restaurantRepository.findByRestaurantEmail(restaurantCreateDto.getRestaurantEmail());
        if(existingRestaurant != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant already exists");
        }
        Restaurant newRestaurant = restaurantConverter.fromCreateDto(restaurantCreateDto);
        restaurantRepository.save(newRestaurant);
        return restaurantConverter.toDto(newRestaurant);
    }


}