package com.example.project1.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.service.SupportClientService;

import org.springframework.http.ResponseEntity; 

@RestController
@RequestMapping("/api/support")
public class SupportController {

    private final SupportClientService supportClientService;

    public SupportController(SupportClientService supportClientService) {
        this.supportClientService = supportClientService;
    }

    @PostMapping("/report")
    public ResponseEntity<String> reportIssue(
            @RequestParam String email,
            @RequestParam String message) {
        // Повикај ја логиката од сервисот
        String response = supportClientService.reportIssue(email, message);

        // Врати го одговорот до клиентот
        return ResponseEntity.ok(response);
    }
}
