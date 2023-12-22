package gsc.projects.orderservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private UUID uuidOrder;

    private String restaurantEmail;

    private String userEmail;

    @ElementCollection
    private Map<String, Double> foodAndQuantity;

    public static OrderBuilder build(){
        return new OrderBuilder();
    }

    public static class OrderBuilder{

        private final Order order;

        public OrderBuilder() {
            this.order = new Order();
        }
        public OrderBuilder withUUID(UUID uuid){
            order.setUuidOrder(uuid);
            return this;
        }

        public OrderBuilder withRestaurantEmail(String restaurantEmail){
            order.setRestaurantEmail(restaurantEmail);
            return this;
        }

        public OrderBuilder withUserEmail(String userEmail){
            order.setUserEmail(userEmail);
            return this;
        }

        public OrderBuilder withFoodAndQuantity(Map<String, Double> foodAndQuantity){
            order.setFoodAndQuantity(foodAndQuantity);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
