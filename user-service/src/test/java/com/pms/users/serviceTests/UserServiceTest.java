package com.pms.users.serviceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pms.users.exceptions.ResourceNotFoundException;
import com.pms.users.model.User;
import com.pms.users.repository.UserRepository;
import com.pms.users.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        passwordEncoder=new BCryptPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);
        User user=new User("swap101","pass");
        userRepository.save(user);
    }

    @Test
    public void saveUser_NewUser_SuccessfullySavesUser() throws ResourceNotFoundException {
        User user = new User();
        user.setName("testuser");
        user.setPassword("password");
        when(userRepository.findByName(user.getName())).thenReturn(Optional.empty());
        when(userRepository.insert(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);

        assertEquals(user, savedUser);
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userRepository, times(1)).findByName(user.getName());
        verify(userRepository, times(1)).insert(user);
    }

    @Test
    public void saveUser_ExistingUser_ThrowsResourceNotFoundException() {
        // Arrange
        User user = new User();
        user.setName("testuser");
        user.setPassword("password");
        when(userRepository.findByName(user.getName())).thenReturn(Optional.of(user));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.saveUser(user));
        verify(userRepository, times(1)).findByName(user.getName());
        verify(userRepository, never()).insert(any(User.class));
    }

    @Test
    public void getAll_ReturnsAllUsers() {
        List<User> users = new ArrayList<>();
        User user=new User();
        user.setUserId(1);
        user.setFullName("sp");
        user.setName("spp");
        user.setContact("7350593612");
        user.setEmail("pass@spp");
        user.setPassword("pass");
        users.add(user);
        userRepository.insert(user);
        when(userService.getAll()).thenReturn(users);

        List<User> result = userRepository.findAll();

        assertEquals(users, result);
        //verify(userRepository, times(1)).findAll();
    }

    // Add more test cases for other methods in UserService
}
