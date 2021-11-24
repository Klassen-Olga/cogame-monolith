package de.cogamemonolith.web.service;

import de.cogamemonolith.exception.NotFoundException;
import de.cogamemonolith.model.Message;
import de.cogamemonolith.model.User;
import de.cogamemonolith.repository.MessageRepository;
import de.cogamemonolith.web.dto.in.MessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    MessageRepository messageRepository;

    public Message save(MessageRequest messageRequest, User creator) {

        Message message = new Message();
        message.setText(messageRequest.getText());
        message.setUser(creator);
        return messageRepository.save(message);
    }

    public Message getMessage(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("Message with id " + messageId + " not found"));
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }
}
