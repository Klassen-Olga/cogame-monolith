package de.cogamemonolith.web.controller;



import de.cogamemonolith.exception.NotFoundException;
import de.cogamemonolith.exception.UniqueKeyViolation;
import de.cogamemonolith.model.User;
import de.cogamemonolith.repository.UserRepository;
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




    @GetMapping("/users")
    public List<User> getUsers() {

        return userRepository.findAll();
    }



    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {

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

//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable Long id) {
//
//        Optional<User> user = userRepository.findById(id);
//
//        if (!user.isPresent()) {
//            throw new NotFoundException("User with id " + id + " does not exist");
//        }
//        userRepository.deleteById(id);
//
//    }
//
//    @PutMapping("/users/{id}")
//    public void updateUser(@RequestBody User user) {
//
//        if (userRepository.findById(user.getId())) {
//            throw new NotFoundException("User with id " + user.getId() + " does not exist");
//        }
//
//        userRepository.save(user);
//
//    }

}
