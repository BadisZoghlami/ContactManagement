package com.contact.management.service;

import com.contact.management.model.Company;
import com.contact.management.model.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company getCompanyById(Long companyId);

    List<Company> getAllCompanies();

    void createCompany(CompanyDto company);

    void updateCompany(Long id, CompanyDto company);

    void deleteCompany(Long id);

    Company findCompanyByTvaNumber(String tvaNumber);
}
