package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.findById(id).orElseThrow();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userService.save(user);
    }

    @DeleteMapping("/{id")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
