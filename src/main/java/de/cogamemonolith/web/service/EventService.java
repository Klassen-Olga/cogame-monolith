package de.cogamemonolith.web.service;

import de.cogamemonolith.exception.NotFoundException;
import de.cogamemonolith.model.Event;
import de.cogamemonolith.model.User;
import de.cogamemonolith.repository.EventRepository;
import de.cogamemonolith.web.dto.in.EventCreateRequest;
import de.cogamemonolith.web.dto.out.EventResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor
public class EventService {
    EventRepository eventRepository;
    ModelMapper modelMapper;

    public List<EventResponse> findAll() {
        List<EventResponse> eventResponses = new LinkedList<>();

        List<Event> events = eventRepository.findAll();
        for (Event event : events) {
            EventResponse eventResponse = new EventResponse();
            modelMapper.map(event, eventResponse);
            eventResponses.add(eventResponse);
        }
        return eventResponses;
    }

    public Event getEvent(Long id) {

        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event with the id " + id + " does not exist"));
    }

    public EventResponse getEventResponse(Long id) {

        EventResponse eventResponse = new EventResponse();
        modelMapper.map(getEvent(id), eventResponse);
        return eventResponse;
    }

    public Event save(EventCreateRequest eventCreateRequest, User creator) {
        Event event = new Event();

        modelMapper.map(eventCreateRequest, event);
        event.setCreator(creator);
        Set<User> participants = new HashSet<>();
        participants.add(creator);

        event.setParticipants(participants);

        return eventRepository.save(event);
    }

    public void save(Event event) {
        eventRepository.save(event);
    }


    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public boolean participatesUser(Event event, User user) {


        return event.getParticipants().contains(user);
    }


    public List<Event> findAllByCreator(User user) {
        return eventRepository.findAllByCreator(user);
    }
}
