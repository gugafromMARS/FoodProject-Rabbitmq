package gsc.projects.restaurantservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "food")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private double price;

    @ManyToMany
    @JsonIgnore
    private List<RestaurantMenu> menus;


    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public static FoodBuilder builder(){
        return new FoodBuilder();
    }
    public static class FoodBuilder {

        private final Food food;

        public FoodBuilder() {
            this.food = new Food();
        }

        public FoodBuilder withName(String name){
            food.setName(name);
            return this;
        }

        public FoodBuilder withPrice(double price){
            food.setPrice(price);
            return this;
        }

        public Food build(){
            return food;
        }

    }
}
