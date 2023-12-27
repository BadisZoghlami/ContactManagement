package com.contact.management.repository;

import com.contact.management.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByTvaNumber(String tvaNumber);
}
