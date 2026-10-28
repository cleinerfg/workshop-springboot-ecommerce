package org.workshop.ecommerce.modules.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.workshop.ecommerce.exceptions.DatabaseException;
import org.workshop.ecommerce.exceptions.ResourceNotFoundException;

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
        return userOp.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public User update(Long id, User userUpdated) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, userUpdated);
            return repository.save(entity);
        } catch (EntityNotFoundException _) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User userUpdated) {
        entity.setName(userUpdated.getName());
        entity.setEmail(userUpdated.getEmail());
        entity.setPhone(userUpdated.getPhone());
    }

}
