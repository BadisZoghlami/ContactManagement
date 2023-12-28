package com.contact.management.service.impl;

import com.contact.management.model.Company;
import com.contact.management.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyServiceImpl;
    @Test
    void testGetCompanyById() {
        // Mock data
        Company mockCompany = new Company();
        mockCompany.setId(1L);

        // Mock behavior
        Mockito.when(companyRepository.findById(1L)).thenReturn(Optional.of(mockCompany));

        // Test
        Company result = companyServiceImpl.getCompanyById(1L);

        // Verify
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId().longValue());
    }
}