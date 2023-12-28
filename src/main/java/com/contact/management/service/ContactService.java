package com.contact.management.service;

import com.contact.management.model.Contact;
import com.contact.management.dto.ContactDto;

import java.util.List;

public interface ContactService {
    Contact getContactById(Long contactId);

    List<Contact> getAllContacts();

    void createContact(ContactDto contact);

    void updateContact(Long id, ContactDto updatedContact);

    void deleteContact(Long id);

    void addContactToCompanies(Long contactId, List<Long> companyIds);
}
