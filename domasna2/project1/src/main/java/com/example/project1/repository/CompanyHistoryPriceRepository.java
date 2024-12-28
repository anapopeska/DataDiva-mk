package com.example.project1.repository;

import com.example.project1.model.CompanyModel;
import com.example.project1.model.CompanyHistoryPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyHistoryPriceRepository extends JpaRepository<CompanyHistoryPriceModel, Long> {
    Optional<CompanyHistoryPriceModel> findByDateAndCompany(LocalDate date, CompanyModel company);
    List<CompanyHistoryPriceModel> findByCompanyIdAndDateBetween(Long companyId, LocalDate from, LocalDate to);
    List<CompanyHistoryPriceModel> findByCompanyId(Long companyId);
    List<CompanyHistoryPriceModel> findAllByDate(LocalDate date);

        List<CompanyHistoryPriceModel> findByCompanyCompanyCode(String companyCode);  // Методата треба да биде нестатична
    }
    



