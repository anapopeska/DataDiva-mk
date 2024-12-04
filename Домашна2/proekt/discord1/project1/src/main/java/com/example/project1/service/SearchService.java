package com.example.project1.service;

import com.example.project1.model.CompanyHistoryPriceModel;
import com.example.project1.model.CompanyModel;
import com.example.project1.repository.CompanyHistoryPriceRepository;
import com.example.project1.repository.CompanyModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final CompanyModelRepository companyModelRepository;
    private final CompanyHistoryPriceRepository companyHistoryPriceRepository;

    public List<CompanyModel> findAll() {
        return companyModelRepository.findAll();
    }

    public CompanyModel findById(Long id) throws Exception {
        return companyModelRepository.findById(id).orElseThrow(Exception::new);
    }

    public List<CompanyHistoryPriceModel> findAllToday() {
        return companyHistoryPriceRepository.findAllByDate(LocalDate.now());
    }

}
