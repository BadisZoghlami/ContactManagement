package com.contact.management.controller;

import com.contact.management.model.Company;
import com.contact.management.model.CompanyDto;
import com.contact.management.service.CompanyService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<Company> contacts = companyService.getAllCompanies();

        List<CompanyDto> companyDtos = contacts.stream()
                .map(contact -> modelMapper.map(contact, CompanyDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getContactById(@PathVariable Long id) {

        CompanyDto companyDto = modelMapper.map(companyService.getCompanyById(id), CompanyDto.class);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody @Valid CompanyDto company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody CompanyDto updatedCompany) {
        companyService.updateCompany(id, updatedCompany);
        return new ResponseEntity<>("Contact updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>("Company deleted", HttpStatus.OK);
    }

    @GetMapping("/tvaNumber/{tvaNumber}")
    public ResponseEntity<CompanyDto> getCompanyByTvaNumber(@PathVariable String tvaNumber) {
        CompanyDto companyDto = modelMapper.map(companyService.findCompanyByTvaNumber(tvaNumber), CompanyDto.class);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }
}
