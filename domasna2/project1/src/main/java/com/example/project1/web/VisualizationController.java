package com.example.project1.web;

import com.example.project1.model.CompanyHistoryPriceModel;
import com.example.project1.service.VisualizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/visualization")
@RequiredArgsConstructor
public class VisualizationController {

    private final VisualizationService visualizationService;

    @GetMapping("/getCompanyHistory")
    public ResponseEntity<List<CompanyHistoryPriceModel>> getCompanyHistory(@RequestParam String companyCode) {
        // Повик до сервисот за податоците
        List<CompanyHistoryPriceModel> companyHistory = visualizationService.getCompanyHistory(companyCode);
        return ResponseEntity.ok(companyHistory);
    }
}
