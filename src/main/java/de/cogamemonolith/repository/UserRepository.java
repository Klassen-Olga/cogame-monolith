package de.cogamemonolith.repository;


import java.util.List;

import de.cogamemonolith.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository which manages database operations of the User model
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
