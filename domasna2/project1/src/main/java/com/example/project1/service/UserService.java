package com.example.project1.service;

import com.example.project1.model.User;
import com.example.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project1.Logger;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = Logger.getInstance(); // Singleton Logger

    public User saveUser(User user) {
        logger.log("Saving user: " + user.getUsername());
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        logger.log("Searching for user: " + username);
        return userRepository.findByUsername(username);
    }

    public void registerUser(String username, String rawPassword) {
        logger.log("Registering user: " + username);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(rawPassword);

        userRepository.save(newUser);
        logger.log("User registered successfully: " + username);
    }
}