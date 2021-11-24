package de.cogamemonolith.web.service;

import de.cogamemonolith.exception.NotFoundException;
import de.cogamemonolith.model.User;
import de.cogamemonolith.repository.UserRepository;
import de.cogamemonolith.web.dto.in.UserCreateRequest;
import de.cogamemonolith.web.dto.out.UserResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()-> {
            throw new NotFoundException("User with id " + id + " does not exist");
        });

    }

    public UserResponse getUserResponse(Long id) {
        UserResponse userResponse = new UserResponse();
        User user = getUser(id);
        modelMapper.map(user, userResponse);
        return userResponse;
    }

    public List<UserResponse> findAllUserResponses() {
        List<User> all = findAll();
        LinkedList<UserResponse> userResponses = new LinkedList<>();
        for (User user:all){
            UserResponse userResponse = new UserResponse();
            modelMapper.map(user, userResponse);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public User save(UserCreateRequest user) {
        User user1 = new User();
        modelMapper.map(user, user1);
        return userRepository.save(user1);

    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
