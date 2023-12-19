package gsc.projects.restaurantservice.repository;


import gsc.projects.restaurantservice.model.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<RestaurantMenu, Long> {
}
