package com.contact.management.service;

import com.contact.management.model.Company;
import com.contact.management.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(Long companyId);

    List<Company> getAllCompanies();

    void createCompany(CompanyDto company);

    void updateCompany(Long id, CompanyDto company);

    Company findCompanyByTvaNumber(String tvaNumber);
}
