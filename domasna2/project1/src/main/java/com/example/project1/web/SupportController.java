package com.example.project1.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity; 

@RestController
@RequestMapping("/api/support")
public class SupportController {

    @PostMapping("/report")
    public ResponseEntity<String> reportIssue(@RequestParam String email, @RequestParam String message) {
        // Логирање на проблемот
        System.out.println("Problem is reported");
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

        // Можеш да го зачуваш во база или да пратиш email
        return ResponseEntity.ok("The problem is successfully reported.");
    }
}