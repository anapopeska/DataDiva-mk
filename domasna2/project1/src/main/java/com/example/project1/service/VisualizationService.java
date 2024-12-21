package com.example.project1.service;

import com.example.project1.model.CompanyHistoryPriceModel;
import com.example.project1.repository.CompanyHistoryPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisualizationService {

    private final CompanyHistoryPriceRepository companyHistoryPriceRepository;

    public List<CompanyHistoryPriceModel> getCompanyHistory(String companyCode) {
        // Преземање на сите податоци за дадената компанија
        return companyHistoryPriceRepository.findByCompanyCompanyCode(companyCode);
    }
}
