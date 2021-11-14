package de.cogamemonolith.repository;

import de.cogamemonolith.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository which manages database operations of the Message model
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByEventId(String eventId);

    void deleteMessagesById(String id);
}
