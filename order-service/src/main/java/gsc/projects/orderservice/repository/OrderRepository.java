package gsc.projects.orderservice.repository;


import gsc.projects.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUuidOrder(UUID uuid);
    List<Order> findByUserEmail(String userEmail);
}
