package gsc.projects.restaurantservice.controller;


import gsc.projects.restaurantservice.dto.RestaurantCreateDto;
import gsc.projects.restaurantservice.dto.RestaurantUpdateAdd;
import gsc.projects.restaurantservice.dto.RestaurantUpdateEmail;
import gsc.projects.restaurantservice.dto.RestaurantUpdateRemove;
import gsc.projects.restaurantservice.service.RestaurantServiceImp;
import lombok.AllArgsConstructor;
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

    @GetMapping
    public ResponseEntity<?> getRestaurant(@RequestParam ("name") String name){
        return ResponseEntity.ok(restaurantServiceImp.getByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(restaurantServiceImp.getAllRestaurants());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRestaurant(@RequestParam ("id") Long id){
        restaurantServiceImp.deleteById(id);
        return ResponseEntity.ok("Restaurant deleted successfully");
    }

    @PutMapping("/{id}/remove")
    public ResponseEntity<?> removeMenu(@PathVariable("id") Long id, @RequestBody RestaurantUpdateRemove restaurantUpdateRemove){
        return ResponseEntity.ok(restaurantServiceImp.removeOnMenu(id, restaurantUpdateRemove));
    }
    @PutMapping( "/{id}/add")
    public ResponseEntity<?> addMenu(@PathVariable("id") Long id, @RequestBody RestaurantUpdateAdd restaurantUpdateAdd){
        return ResponseEntity.ok(restaurantServiceImp.addOnMenu(id, restaurantUpdateAdd));
    }
    @PutMapping("/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable("id") Long id, @RequestBody RestaurantUpdateEmail restaurantUpdateEmail){
        return ResponseEntity.ok(restaurantServiceImp.updateRestaurantEmail(id, restaurantUpdateEmail));
    }
}
