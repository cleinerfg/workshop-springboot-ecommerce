package org.workshop.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.workshop.ecommerce.entities.Order;
import org.workshop.ecommerce.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public List<Order> loadAll() {
        return repository.findAll();
    }

    public Order loadById(Long id) {
        Optional<Order> orderOp = repository.findById(id);
        return orderOp.orElse(null);
    }
}
