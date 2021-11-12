package de.cogamemonolith.user.web;



import de.cogamemonolith.exception.NotFoundException;
import de.cogamemonolith.exception.UniqueKeyViolation;
import de.cogamemonolith.user.model.User;
import de.cogamemonolith.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Restapi controller for user-service module
 * Manages get, post, put and delete methods
 */
@AllArgsConstructor
@RestController
@Log4j2
public class UserController {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    @GetMapping("/greeting")
    public String greeting1() {
        return "Hello from user-service";
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    /**
     * Used to get all users objects based on the list of the ids
     * @param ids list of ids
     * @return list of users
     */
    @GetMapping("/certain-users")
    public List<User> getCertainUsers(@RequestBody List<String> ids) {

        return userRepository.getAllByIdIn(ids);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new NotFoundException("User with id " + id + " does not exist");
        }

        return user.get();

    }


    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));

        try {
            User savedUser = userRepository.save(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RuntimeException exception) {
            // if exception is of type E11000 throw high level exception
            if (exception.getMessage().contains("E11000 duplicate key error")) {
                throw new UniqueKeyViolation(exception.getMessage());
            }
            throw exception;
        }


    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new NotFoundException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);

    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User user) {

        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new NotFoundException("User with id " + user.getId() + " does not exist");
        }

        userRepository.save(user);

    }

}
