package de.cogamemonolith.initializr;


import de.cogamemonolith.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Class will be used for db initialising, creates Event instance
 */
public class EventInitializr {

    /**
     * @return an initialized event
     */
    public static Event  getEvent(String name, Set<User> participants, User creator, Set<Message> messages) {
        List<Activity> activities = new LinkedList<>();
        Activity activity1=new Activity();
        Activity activity2=new Activity();
        activity1.setActivityArt("TABLE");
        activity1.setName("Monopoly");
        activity2.setActivityArt("ACTIVE");
        activity2.setName("Twister");

        activities.add(activity1);
        activities.add(activity2);



        List<Tool> tools = new LinkedList<>();
        Tool tool1 = new Tool();
        tool1.setToolName("Monopoly game");
        tool1.setAlreadyExists(true);
        Tool tool2 = new Tool();
        tool2.setToolName("Twister game");
        tool2.setAlreadyExists(false);
        tools.add(tool1);

        Address address = new Address();
        address.setCity("Erfurt");
        address.setCountry("Germany");
        address.setStreetAndHomeNumber("Norweische Street 15");

        Event event = new Event(
                null,
                name,
                "A party where we are going to play both monopoly and twister",
                LocalDateTime.of(LocalDate.of(2022, 1, 1), LocalTime.of(12, 0)),
                10,
                address,
                activities,
                tools,
                messages,
                creator,
                participants,
                LocalDateTime.now()
                );
        event.setCreatedAt(LocalDateTime.of(LocalDate.of(2022, 1, 1), LocalTime.of(12, 0)));
        return event;
    }
}
