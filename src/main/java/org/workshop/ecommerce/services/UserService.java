package org.workshop.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.workshop.ecommerce.entities.User;
import org.workshop.ecommerce.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> loadAll() {
        return repository.findAll();
    }

    public User loadById(Long id) {
        Optional<User> userOp = repository.findById(id);
        return userOp.orElse(null);
    }
}
