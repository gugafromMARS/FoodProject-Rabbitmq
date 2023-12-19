package gsc.projects.restaurantservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    //EMBEDDED ou ELEMENTAL collectioon para nao ter de estar a fazer entidades.
    @ManyToOne
    private RestaurantMenu restaurantMenu;

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

        public RestaurantBuilder withMenu(RestaurantMenu restaurantMenu){
            restaurant.setRestaurantMenu(restaurantMenu);
            return this;
        }

        public Restaurant build(){
            return restaurant;
        }
    }

}
