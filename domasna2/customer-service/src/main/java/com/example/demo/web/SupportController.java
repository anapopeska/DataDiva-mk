package com.example.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/support")
public class SupportController {

    @PostMapping("/report")
    public ResponseEntity<String> reportIssue(
            @RequestParam String email,
            @RequestParam String message) {
        // Логирање на податоците
        System.out.println("Problem reported by: " + email);
        System.out.println("Message: " + message);

        // Одговор до клиентот
        return ResponseEntity.ok("Your issue has been reported successfully!");
    }
}