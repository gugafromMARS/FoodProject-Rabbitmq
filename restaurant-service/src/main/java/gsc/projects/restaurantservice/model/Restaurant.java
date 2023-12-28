package gsc.projects.restaurantservice.model;


import gsc.projects.restaurantservice.dto.OrderEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String restaurantEmail;

    private String address;

    @ElementCollection
    private Map<String, Double> menu;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orderList;

    public static RestaurantBuilder builder(){
        return new RestaurantBuilder();
    }

    public static class RestaurantBuilder {

        private final Restaurant restaurant;

        public RestaurantBuilder() {
            this.restaurant = new Restaurant();
        }

        public RestaurantBuilder withName(String name){
            restaurant.setName(name);
            return this;
        }

        public RestaurantBuilder withEmail(String email){
            restaurant.setRestaurantEmail(email);
            return this;
        }

        public RestaurantBuilder withAddress(String address){
            restaurant.setAddress(address);
            return this;
        }

        public RestaurantBuilder withMenu(Map<String, Double> menu){
            restaurant.setMenu(menu);
            return this;
        }

        public RestaurantBuilder withOrderList(){
            restaurant.setOrderList(new ArrayList<>());
            return this;
        }

        public Restaurant build(){
            return restaurant;
        }
    }

}
