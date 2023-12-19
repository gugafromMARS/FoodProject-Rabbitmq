package gsc.projects.restaurantservice.controller;


import gsc.projects.restaurantservice.dto.RestaurantCreateDto;
import gsc.projects.restaurantservice.service.RestaurantServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantServiceImp restaurantServiceImp;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RestaurantCreateDto restaurantCreateDto){
        return new ResponseEntity<>(restaurantServiceImp.createRestaurant(restaurantCreateDto), HttpStatus.CREATED);
    }

}
