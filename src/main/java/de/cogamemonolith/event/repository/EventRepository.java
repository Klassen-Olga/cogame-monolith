package de.cogamemonolith.event.repository;

import de.cogamemonolith.event.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository which manages database operations of the Event model
 */
public interface EventRepository extends MongoRepository<Event, String> {
}
