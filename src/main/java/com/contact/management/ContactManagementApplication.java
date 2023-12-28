package com.contact.management;

import com.contact.management.dto.CompanyDto;
import com.contact.management.dto.ContactDto;
import com.contact.management.service.CompanyService;
import com.contact.management.service.ContactService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class ContactManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactManagementApplication.class, args);
    }
    @Bean
    public CommandLineRunner initData(ContactService contactService, CompanyService companyService) {
        return args -> {
            // Create example companies
            CompanyDto company1 = new CompanyDto("Company 1","123 Main Street","123456789");
            CompanyDto company2 = new CompanyDto("Company 2","456 Main Street","987654321");

            // Save companies
            companyService.createCompany(company1);
            companyService.createCompany(company2);

            // Create example contacts
            ContactDto contact1 = new ContactDto("John", "Doe", "789 Elm Lane", false, null, null);
            ContactDto contact2 = new ContactDto("Jane", "Smith", "101 Pine Road", true, "1111", null);

            // Save contacts
            contactService.createContact(contact1);
            contactService.createContact(contact2);

            // Add Contact to Companies
            contactService.addContactToCompanies(1L, Collections.singletonList(1L));
            contactService.addContactToCompanies(2L, Collections.singletonList(2L));
        };
    }
}
