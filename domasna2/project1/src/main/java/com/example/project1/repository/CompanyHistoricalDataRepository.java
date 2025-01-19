package com.example.project1.repository;

import com.example.project1.model.CompanyModel;
import com.example.project1.model.CompanyHistoricalDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyHistoricalDataRepository extends JpaRepository<CompanyHistoricalDataModel, Long> {
    Optional<CompanyHistoricalDataModel> findByDateAndCompany(LocalDate date, CompanyModel company);
    List<CompanyHistoricalDataModel> findByCompanyIdAndDateBetween(Long companyId, LocalDate from, LocalDate to);
    List<CompanyHistoricalDataModel> findByCompanyId(Long companyId);
    List<CompanyHistoricalDataModel> findAllByDate(LocalDate date);

        List<CompanyHistoricalDataModel> findByCompanyCompanyCode(String companyCode);  // Методата треба да биде нестатична
    }
    



