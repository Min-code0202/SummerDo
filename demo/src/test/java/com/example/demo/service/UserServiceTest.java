package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp(){
        user = new User();
        user.setId(1L);
        user.setName("Kim test");
        user.setEmail("kimtest.doe@example.com");
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userService.findAll();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user.getName(), users.get(0).getName());
        assertEquals(user.getEmail(), users.get(0).getEmail());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getName(), foundUser.get().getName());
        assertEquals(user.getEmail(), foundUser.get().getEmail());

        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void save() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getEmail(), savedUser.getEmail());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteById() {
        doNothing().when(userRepository).deleteById(user.getId());

        userService.deleteById(user.getId());

        verify(userRepository, times(1)).deleteById(user.getId());
    }
}
