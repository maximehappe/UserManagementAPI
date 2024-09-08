package com.example.usermanagementapi.user.service;

import org.springframework.stereotype.Service;
import com.example.usermanagementapi.user.database.repository.UserRepository;
import com.example.usermanagementapi.user.database.entity.User;

import java.util.List;
import java.util.Optional;
import lombok.*;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> searchUsersByName(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }
}