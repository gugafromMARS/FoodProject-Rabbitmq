package gsc.projects.restaurantservice.controller;


import gsc.projects.restaurantservice.dto.RestaurantCreateDto;
import gsc.projects.restaurantservice.service.RestaurantServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantServiceImp restaurantServiceImp;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RestaurantCreateDto restaurantCreateDto){
        return new ResponseEntity<>(restaurantServiceImp.createRestaurant(restaurantCreateDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getRestaurant(@RequestParam ("name") String name){
        return ResponseEntity.ok(restaurantServiceImp.getByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(restaurantServiceImp.getAllRestaurants());
    }
}
