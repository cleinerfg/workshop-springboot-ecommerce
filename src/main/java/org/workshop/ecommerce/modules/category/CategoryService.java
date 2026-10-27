package org.workshop.ecommerce.modules.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> loadAll() {
        return repository.findAll();
    }

    public Category loadById(Long id) {
        Optional<Category> orderOp = repository.findById(id);
        return orderOp.orElse(null);
    }
}
