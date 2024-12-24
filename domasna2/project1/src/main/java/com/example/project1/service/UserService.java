package com.example.project1.service;

import com.example.project1.model.User;
import com.example.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(String username, String rawPassword) {
        // Креирај нов корисник
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(rawPassword); // Чувај ја лозинката како што е внесена

        // Зачувај го корисникот во базата
        userRepository.save(newUser);
    }
}