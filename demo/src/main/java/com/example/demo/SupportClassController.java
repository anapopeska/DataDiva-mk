package com.example.demo;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/support")
public class SupportClassController {

    @PostMapping("/report")
    public ResponseEntity<String> reportIssue(
            @RequestParam String email,
            @RequestParam String message) {
        System.out.println("Problem is reported");
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

        // Можеш да зачуваш во база или да испратиш е-пошта.
        return ResponseEntity.ok("The problem is successfully reported.");
    }
}
