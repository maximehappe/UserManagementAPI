package com.example.usermanagementapi;

import com.example.usermanagementapi.user.database.entity.User;
import com.example.usermanagementapi.user.database.repository.UserRepository;
import com.example.usermanagementapi.user.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .role(User.Role.USER)
                .build();
    }

    @Test
    void createUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User createdUser = userService.createUser(testUser);

        assertEquals("testuser", createdUser.getUsername());
        assertEquals("test@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        Optional<User> foundUser = userService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void searchUsersByName() {
        List<User> users = Arrays.asList(testUser, User.builder().username("testuser2").build());
        when(userRepository.findByUsernameContainingIgnoreCase("test")).thenReturn(users);

        List<User> foundUsers = userService.searchUsersByName("test");

        assertFalse(foundUsers.isEmpty());
        assertEquals(2, foundUsers.size());
        verify(userRepository, times(1)).findByUsernameContainingIgnoreCase("test");
    }

    @Test
    void getUserByIdNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.getUserById(2L);

        assertFalse(foundUser.isPresent());
        verify(userRepository, times(1)).findById(2L);
    }

    @Test
    void searchUsersByNameNoResults() {
        when(userRepository.findByUsernameContainingIgnoreCase("nonexistent")).thenReturn(List.of());

        List<User> foundUsers = userService.searchUsersByName("nonexistent");

        assertTrue(foundUsers.isEmpty());
        verify(userRepository, times(1)).findByUsernameContainingIgnoreCase("nonexistent");
    }
}