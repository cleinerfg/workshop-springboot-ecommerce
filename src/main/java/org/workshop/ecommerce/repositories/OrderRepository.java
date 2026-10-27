package org.workshop.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workshop.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
