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

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User userUpdated) {
        User entity = repository.getReferenceById(id);
        updateData(entity, userUpdated);
        return repository.save(entity);
    }

    private void updateData(User entity, User userUpdated) {
        entity.setName(userUpdated.getName());
        entity.setEmail(userUpdated.getEmail());
        entity.setPhone(userUpdated.getPhone());
    }

}
