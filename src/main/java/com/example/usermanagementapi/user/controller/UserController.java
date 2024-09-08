package com.example.usermanagementapi.user.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagementapi.user.service.UserService;
import com.example.usermanagementapi.user.database.entity.User;
import java.util.List;
import lombok.*;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByName(@RequestParam String username) {
        // Implement admin role check
        // e.g. permissionservice.validatePermission(Permission.searchUsers);
        // the method would check if the user has the permission to search users based on the current user's rolea
        List<User> users = userService.searchUsersByName(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

