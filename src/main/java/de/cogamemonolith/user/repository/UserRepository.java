package de.cogamemonolith.user.repository;

import de.cogamemonolith.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository which manages database operations of the User model
 */
public interface UserRepository extends MongoRepository<User, String> {
    List<User> getAllByIdIn(List<String> ids);
}
