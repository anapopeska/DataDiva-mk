package com.example.project1.web;

import com.example.project1.model.User;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getLogPage() {
        return "log"; // Враќа log.html
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok("Успешно логирање!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Грешно корисничко име или лозинка!");
        }
    }
    @GetMapping
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Грешнa лозинка!");
        }

        if (userService.findByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Грешнa лозинка!");
        }

        userService.registerUser(username, password);
        return ResponseEntity.ok("Успешно логирање!");
    }
    
}