package org.workshop.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workshop.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
