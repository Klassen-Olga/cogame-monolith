package de.cogamemonolith.web.controller;


import de.cogamemonolith.exception.EventConstraintViolation;
import de.cogamemonolith.exception.UniqueKeyViolation;
import de.cogamemonolith.model.Event;
import de.cogamemonolith.model.User;
import de.cogamemonolith.web.dto.in.UserCreateRequest;
import de.cogamemonolith.web.dto.out.UserResponse;
import de.cogamemonolith.web.service.EventService;
import de.cogamemonolith.web.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Restapi controller for user-service module
 * Manages get, post, put and delete methods
 */
@AllArgsConstructor
@RestController
public class UserController {

    UserService userService;
    EventService eventService;
    PasswordEncoder passwordEncoder;


    @GetMapping("/users")
    public List<UserResponse> getUsers() {

        return userService.getAll();

    }


    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable Long id) {

        return userService.getById(id);

    }


    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateRequest user) {

        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));

        try {
            User savedUser = userService.save(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RuntimeException exception) {
            // if exception is of type E11000 throw high level exception
            if (exception.getMessage().contains("ConstraintViolationException")) {
                throw new UniqueKeyViolation(exception.getMessage());
            }
            throw exception;
        }


    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {

        // check if user exists
        User user = userService.getUser(id);
        //check if user has event created
        List<Event> events = eventService.findAllByCreator(user);
        if (userService.areEventsInFuture(events)) {
            throw new EventConstraintViolation("An user with active created events can not be deleted");
        }
        // delete user
        userService.delete(id);

    }


}
