package de.cogamemonolith.initializr;

import de.cogamemonolith.model.Event;
import de.cogamemonolith.model.Message;
import de.cogamemonolith.model.User;
import de.cogamemonolith.repository.EventRepository;
import de.cogamemonolith.repository.MessageRepository;
import de.cogamemonolith.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//  @Component
@AllArgsConstructor
public class Initializr {
    UserRepository userRepository;
    MessageRepository messageRepository;
    EventRepository eventRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        User albert = UserInitializr.getUser("Albert");
        User mona = UserInitializr.getUser("Mona");
        albert = userRepository.save(albert);
        mona = userRepository.save(mona);
        Set<User> users = new HashSet<>();
        users.add(albert);
        users.add(mona);
        Set<Message> messages = MessageInitializr.getMessages(albert);
        Set<Message> savedMessages = new HashSet<>();
        for (Message message : messages) {
            savedMessages.add(messageRepository.save(message));
        }
        Event friends_evening = EventInitializr.getEvent("Friends evening", users, albert, messages);
        friends_evening=eventRepository.save(friends_evening);

        Set<Event> events=new HashSet<>();
        events.add(friends_evening);


    }
}
