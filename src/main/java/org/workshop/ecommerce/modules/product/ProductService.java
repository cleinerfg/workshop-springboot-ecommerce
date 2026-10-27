package org.workshop.ecommerce.modules.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> loadAll() {
        return repository.findAll();
    }

    public Product loadById(Long id) {
        Optional<Product> orderOp = repository.findById(id);
        return orderOp.orElse(null);
    }
}
