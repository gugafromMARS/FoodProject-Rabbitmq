package gsc.projects.restaurantservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "restaurant_order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private UUID uuidOrder;

    private String userName;

    private String userAddress;

    private String userEmail;

    @ElementCollection
    private Map<String, Double> foodAndQuantity;

    @ManyToOne
    private Restaurant restaurant;

}
