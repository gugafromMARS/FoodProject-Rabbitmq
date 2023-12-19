package gsc.projects.restaurantservice.repository;

import gsc.projects.restaurantservice.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {


}
