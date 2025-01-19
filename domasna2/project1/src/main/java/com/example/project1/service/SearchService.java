package com.example.project1.service;

import com.example.project1.model.CompanyHistoricalDataModel;
import com.example.project1.model.CompanyModel;
import com.example.project1.repository.CompanyHistoricalDataRepository;
import com.example.project1.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final CompanyRepository companyModelRepository;
    private final CompanyHistoricalDataRepository companyHistoryPriceRepository;

    public List<CompanyModel> findAll() {
        return companyModelRepository.findAll();
    }

    public CompanyModel findById(Long id) throws Exception {
        return companyModelRepository.findById(id).orElseThrow(Exception::new);
    }

    public List<CompanyHistoricalDataModel> findAllToday() {
        return companyHistoryPriceRepository.findAllByDate(LocalDate.now());
    }
    public List<CompanyHistoricalDataModel> findHistoryByCompanyCode(String companyCode) {
        return companyHistoryPriceRepository.findByCompanyCompanyCode(companyCode);
    }




}
