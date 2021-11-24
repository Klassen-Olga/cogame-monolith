package de.cogamemonolith.repository;


import de.cogamemonolith.initializer.PostgresInitializer;
import de.cogamemonolith.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = PostgresInitializer.class)
public class RepositoryTests {

    @Autowired
    EventRepository eventRepository;
    //@Autowired UserRepository userRepository;
    //@Autowired MessageRepository messageRepository;

//    @BeforeEach
//    public void resetDb(){
//        List<Event> all = eventRepository.findAll();
//        System.out.println("");
//    }
    @Test
    public void initDb(){

        List<Event> all = eventRepository.findAll();
        System.out.println("");

    }
}
