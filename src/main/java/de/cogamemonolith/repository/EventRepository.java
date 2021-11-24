package de.cogamemonolith.repository;

import de.cogamemonolith.model.Event;
import de.cogamemonolith.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository which manages database operations of the Event model
 */
public interface  EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();
    List<Event> findAllByCreator(User user);
}
