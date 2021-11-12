package de.cogamemonolith.message.repository;

import de.cogamemonolith.message.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository which manages database operations of the Message model
 */
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByEventId(String eventId);

    void deleteMessagesById(String id);
}
