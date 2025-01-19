package com.example.project1.web;

import com.example.project1.Logger; // Импорт на Logger
import com.example.project1.service.UserService;
import com.example.project1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = Logger.getInstance(); // Добиј Singleton инстанца

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        logger.log("Login attempt for user: " + username);

        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            logger.log("Login successful for user: " + username);
            return ResponseEntity.ok("Login successful!");
        } else if (user.isPresent()) {
            logger.log("Login failed: Incorrect password for user: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password!");
        } else {
            logger.log("Login failed: User does not exist: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword) {

        logger.log("Registration attempt for user: " + username);

        if (!password.equals(confirmPassword)) {
            logger.log("Registration failed: Passwords do not match for user: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Passwords do not match!");
        }

        if (userService.findByUsername(username).isPresent()) {
            logger.log("Registration failed: Username already taken: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username is taken");
        }

        userService.registerUser(username, password);
        logger.log("Registration successful for user: " + username);
        return ResponseEntity.ok("User registered successfully!");
    }
}
