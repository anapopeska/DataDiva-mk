package com.example.project1.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CodeController {

    @PostMapping("/process-selected-code")
public ResponseEntity<Map<String, String>> processSelectedCode(@RequestBody Map<String, String> requestBody) {
    String selectedCompanyId = requestBody.get("selectedCompanyId");

    // Проверка дали е добиена вредноста за selectedCompanyId
    if (selectedCompanyId == null || selectedCompanyId.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No company ID provided"));
    }

    // Обработка на кодот или некоја логика
    System.out.println("Received company ID: " + selectedCompanyId);

    // Враќање на текстот во JSON формат
    return ResponseEntity.ok(Map.of("message", "Code processed successfully"));
}
}

