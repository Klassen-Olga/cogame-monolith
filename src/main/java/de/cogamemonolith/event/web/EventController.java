package de.cogamemonolith.event.web;


import de.cogamemonolith.event.model.Event;
import de.cogamemonolith.event.repository.EventRepository;
import de.cogamemonolith.exception.NotFoundException;
import de.cogamemonolith.exception.NumberOfParticipantsReached;
import de.cogamemonolith.message.model.Message;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Restapi controller for event-service module
 * Manages get, post, put and delete methods
 */
@Log4j2
@AllArgsConstructor
@RestController
public class EventController {

    EventRepository eventRepository;


    @GetMapping("/greeting")
    public String greeting() {

        return "Hello from event service!";
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable String id) {

        return getEventOrThrowNotFoundException(id);
    }

    /**
     * Makes a GET request to the message-service module and picks all messages of the event
     *
     * @param id of event which messages are required
     * @return a list with messages
     */
//    @GetMapping("/events/{id}/messages")
//    // while ($true) { curl http://localhost:8000/events/1/messages | Out-Host; Sleep 0.0001;  }
//    public List<Message> getMessagesOfEvent(@PathVariable String id) {
//
//        getEventOrThrowNotFoundException(id);
//        log.error("Message service unreachable");
//        return message
//    }

    /**
     * @param id of event which users are required
     * @return a map of the users with key user-id and value user-name
     */
    @GetMapping("/events/{id}/users")
    public Map<String, String> getUsersOfEvent(@PathVariable String id) {

        Event event = getEventOrThrowNotFoundException(id);
        return event.getParticipants();
    }

    /**
     * Saves an event into the database and returns 201 created status code
     */
    @PostMapping("/events")
    public ResponseEntity<Object> createEvent(@Valid @RequestBody Event event) {

        Event newEvent = eventRepository.save(event);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(newEvent.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable String id) {

        getEventOrThrowNotFoundException(id);
        eventRepository.deleteById(id);
    }

    @PutMapping("/events")
    public void changeEvent(@Valid @RequestBody Event event) {

        getEventOrThrowNotFoundException(event.getId());
        eventRepository.save(event);

    }

    /*
     * Adds an user to the existing event
     * Request format for the request body  {"id":"1", "name":"myName"}
     *
     * */
    @PutMapping("/events/{eventId}/users")
    public void addUser(@Valid @RequestBody @ApiParam(
            value = "Request format {\"id\":\"1\", \"name\":\"myName\"}")
                                Map<String, String> user, @PathVariable String eventId) {

        Event event = getEventOrThrowNotFoundException(eventId);
        if (user.get("id").isBlank()) {
            throw new NotFoundException("Event with id " + eventId + " does not exist");
        }
        int participantsNumber = event.getParticipantsNumber();
        if (participantsNumber == event.getParticipants().size()) {
            throw new NumberOfParticipantsReached("Maximum number of participants " + participantsNumber + " reached");
        }
        event.getParticipants().put(user.get("id"), user.get("name"));
        eventRepository.save(event);
    }

    /*
     * Removes an user from the existing event
     *
     * */
    @PutMapping("/events/{eventId}/users/{userId}")
    public void deleteUser(@PathVariable String userId, @PathVariable String eventId) {

        Event event = getEventOrThrowNotFoundException(eventId);
        Map<String, String> participants = event.getParticipants();
        String exists=participants.remove(userId);
        if (exists==null){
            throw new NotFoundException("User with the id " +userId+" does not exist in event with the id "+eventId);
        }
        eventRepository.save(event);
    }

    public Event getEventOrThrowNotFoundException(String id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            throw new NotFoundException("Event with the id " + id + " does not exist");
        }
        return event.get();
    }



}
