package com.contact.management.service.impl;

import com.contact.management.model.Contact;
import com.contact.management.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private CompanyServiceImpl companyServiceImpl;

    @InjectMocks
    private ContactServiceImpl contactServiceImpl;

    // Write tests for ContactServiceImpl methods
    @Test
    public void testGetContactById() {
        // Mock data
//        Contact mockContact = new Contact();
//        mockContact.setId(1L);
//
//        // Mock behavior
//        Mockito.when(contactRepository.findById(1L)).thenReturn(Optional.of(mockContact));
//
//        // Test
//        Contact result = contactServiceImpl.getContactById(1L);
//
//        // Verify
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(1L, result.getId().longValue());
    }
}