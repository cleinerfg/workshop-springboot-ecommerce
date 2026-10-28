package org.workshop.ecommerce.modules.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public User create(User user) {
        return repository.save(user);
    }
}
