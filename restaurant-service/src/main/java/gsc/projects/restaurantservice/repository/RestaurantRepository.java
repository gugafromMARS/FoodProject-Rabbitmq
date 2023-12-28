package gsc.projects.restaurantservice.repository;


import gsc.projects.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findByRestaurantEmail(String email);
    Restaurant findByName(String name);
}
