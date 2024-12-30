package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.PasswordService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        // Generate password based on first name, middle name, and last name
        String password = passwordService.generatePassword(user.getFirstName(), user.getMiddleName(), user.getLastName());
        user.setPassword(password);

        // Save user to the database
        return userRepository.save(user);
    }
}
