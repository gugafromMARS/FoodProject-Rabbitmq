package gsc.projects.restaurantservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JsonIgnore
    private List<Food> foodList;

    @OneToMany
    @JsonIgnore
    private List<Restaurant> restaurants;

    public static RestaurantMenuBuilder builder(){
        return new RestaurantMenuBuilder();
    }

    public static class RestaurantMenuBuilder{

        private RestaurantMenu restaurantMenu;

        public RestaurantMenuBuilder() {
            this.restaurantMenu = new RestaurantMenu();
        }

        public RestaurantMenuBuilder withFoodList(List<Food> foodList){
            restaurantMenu.setFoodList(foodList);
            return this;
        }

        public RestaurantMenu build(){
            return restaurantMenu;
        }
    }
}
