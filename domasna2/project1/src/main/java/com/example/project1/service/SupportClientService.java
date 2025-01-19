package com.example.project1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SupportClientService {

    private final RestTemplate restTemplate;

    @Value("${support.service.url}")
    private String supportServiceUrl;

    public SupportClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String reportIssue(String email, String message) {
        // Креирај URL со query параметри
        String url = supportServiceUrl + "/support/report?email=" + email + "&message=" + message;

        // Испрати барање до Support Microservice
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        // Врати го одговорот од микросервисот
        return response.getBody();
    }
}

