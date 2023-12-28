package com.contact.management.service.impl;

import com.contact.management.model.Company;
import com.contact.management.dto.CompanyDto;
import com.contact.management.repository.CompanyRepository;
import com.contact.management.service.exception.CompanyNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements com.contact.management.service.CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + companyId));
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(CompanyDto companyDto) {
        Company company = modelMapper.map(companyDto, Company.class);
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Long id, CompanyDto updatedCompanyDto) {
        Company existingCompany = getCompanyById(id);
        modelMapper.map(updatedCompanyDto, existingCompany);
        companyRepository.save(existingCompany);
    }

    @Override
    public Company findCompanyByTvaNumber(String tvaNumber) {
        return companyRepository.findByTvaNumber(tvaNumber)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with tvaNumber: " + tvaNumber));
    }

}
