package de.cogamemonolith.web.controller;


import de.cogamemonolith.exception.EventConstraintViolation;
import de.cogamemonolith.exception.NumberOfParticipantsReached;
import de.cogamemonolith.model.Event;
import de.cogamemonolith.model.Message;
import de.cogamemonolith.model.User;
import de.cogamemonolith.web.dto.in.EventCreateRequest;
import de.cogamemonolith.web.dto.in.MessageRequest;
import de.cogamemonolith.web.dto.out.EventResponse;
import de.cogamemonolith.web.dto.out.MessageResponse;
import de.cogamemonolith.web.dto.out.UserResponse;
import de.cogamemonolith.web.service.EventService;
import de.cogamemonolith.web.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * Restapi controller for event-service module
 * Manages get, post, put and delete methods
 */
@AllArgsConstructor
@RestController
public class EventController {

    EventService eventService;
    UserService userService;


    /**
     * @return all events present in database
     */
    @GetMapping("/events")
    public List<EventResponse> getEventResponses() {

        return eventService.findAll();
    }

    /*
     * @return a certain event
     */
    @GetMapping("/events/{id}")
    public EventResponse getEventResponse(@PathVariable Long id) {

        return eventService.getEventResponse(id);
    }


    /**
     * @param id of event
     * @return all messages of an certain event
     */
    @GetMapping("/events/{id}/messages")
    public List<MessageResponse> getMessageResponsesOfEvent(@PathVariable Long id) {

        EventResponse eventResponse = eventService.getEventResponse(id);
        return eventResponse.getMessages();
    }

    /**
     * @param id of event which users are required
     * @return a set of the users DTO-s
     */
    @GetMapping("/events/{id}/users")
    public Set<UserResponse> getUserResponses(@PathVariable Long id) {

        EventResponse event = getEventResponse(id);

        return event.getParticipants();
    }

    /**
     * Saves an event into the database and returns 201 created status code
     */
    @PostMapping("/events")
    @Transactional
    public ResponseEntity<Object> createEvent(@Valid @RequestBody EventCreateRequest eventCreateRequest) {

        User user = userService.getUser(eventCreateRequest.getCreatorId());
        Event savedEvent = eventService.save(eventCreateRequest, user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(savedEvent.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    /**
     * deletes event and all objects accept users
     *
     * @param id        which even should be deleted
     * @param creatorId an event can be only deleted by creator of event
     */
    @DeleteMapping("/events/{id}")
    @Transactional
    public void deleteEvent(@PathVariable Long id, @RequestParam Long creatorId) {

        // check if event exists
        Event event = eventService.getEvent(id);
        //check if creator is creator of event
        if (!event.getCreator().getId().equals(creatorId)) {
            throw new EventConstraintViolation("User with id " + creatorId + " is not the creator of event with id " + id);
        }
        //messages will be automatic deleted with cascade all

        //deleteEvent
        eventService.deleteEvent(id);

    }

    /**
     * adds an message to the event
     *
     * @param message dto of message entity
     * @param id      event id
     */
    @PostMapping("/events/{id}/messages")
    @Transactional
    public void addMessage(@Valid @RequestBody MessageRequest message, @PathVariable Long id) {

        // check if event exists
        Event event = eventService.getEvent(id);
        //check if user exists
        User user = userService.getUser(message.getUserId());
        //check if user is in event
        if (!eventService.participatesUser(event, user)) {
            throw new EventConstraintViolation("User with id " + message.getUserId() + " does not participates in event with id " + id);
        }
        //save message
        Message savedMessage =eventService.saveMessage(message, user);
        //add message to event
        event.getMessages().add(savedMessage);
        //save event
        eventService.save(event);

    }


    /*
     * Adds an user to the existing event
     *
     * */
    @PutMapping("/events/{eventId}/users/{userId}/add")
    public void addUser(@PathVariable
                                Long userId, @PathVariable Long eventId) {

        //check if event exists
        Event event = eventService.getEvent(eventId);

        //check if user exists
        User user = userService.getUser(userId);
        //check if user already participates
        if (event.getParticipants().contains(user)) {
            throw new EventConstraintViolation("This user already participates in this event");
        }
        //check if participant number not reached
        if (event.getMaximalNumberOfParticipants().equals(event.getParticipants().size())) {
            throw new NumberOfParticipantsReached("Maximum number of participants " + event.getMaximalNumberOfParticipants() + " reached");
        }
        //add new participant to event
        event.getParticipants().add(user);

        //save
        eventService.save(event);
    }

    /*
     * Removes an user from the existing event
     *
     * */
    @PutMapping("/events/{eventId}/users/{userId}/delete")
    public void deleteUser(@PathVariable Long userId, @PathVariable Long eventId) {


        //check if event exists
        Event event = eventService.getEvent(eventId);

        //check if user exists
        User user = userService.getUser(userId);

        //check if user to remove is not creator
        if (userId.equals(event.getCreator().getId())) {
            throw new EventConstraintViolation("Creator of event can not be deleted");
        }
        //check if user participates in event         //remove participant from event
        if (!event.getParticipants().remove(user)) {
            throw new EventConstraintViolation("User with is " + userId + " does not participates in event with id " + eventId);
        }

        //update event
        eventService.save(event);
    }


}
