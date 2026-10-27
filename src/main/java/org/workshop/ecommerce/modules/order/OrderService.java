package org.workshop.ecommerce.modules.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
