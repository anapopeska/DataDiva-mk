package com.example.project1.repository;

import com.example.project1.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyModelRepository extends JpaRepository<CompanyModel, Long> {
    Optional<CompanyModel> findByCompanyCode(String companyCode);
}
