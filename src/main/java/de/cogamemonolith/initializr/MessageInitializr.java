package de.cogamemonolith.initializr;


import de.cogamemonolith.model.Message;
import de.cogamemonolith.model.User;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class will be used for message-api testing and db initialising, creates 3 Message instances
 */
public class MessageInitializr {

    public static Set<Message> getMessages(User user) {

        Message message = new Message(null,user, "A great place",  LocalDateTime.of(2021, 05, 10, 13, 8));
        Message message1 = new Message(null,user, "A great place",  LocalDateTime.of(2021, 05, 10, 13, 8));
        Message message2 = new Message(null,user, "A great place",  LocalDateTime.of(2021, 05, 10, 13, 8));


        Set<Message> messages = new HashSet<>();
        messages.add(message);
        messages.add(message1);
        messages.add(message2);
        return messages;
    }
}
