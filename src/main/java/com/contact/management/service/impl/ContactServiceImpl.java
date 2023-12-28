package com.contact.management.service.impl;

import com.contact.management.model.Company;
import com.contact.management.model.Contact;
import com.contact.management.dto.ContactDto;
import com.contact.management.repository.ContactRepository;
import com.contact.management.service.CompanyService;
import com.contact.management.service.ContactService;
import com.contact.management.service.exception.ContactNotFoundException;
import com.contact.management.service.exception.ContactValidationException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public ContactServiceImpl(ContactRepository contactRepository, CompanyService companyService,
                              ModelMapper modelMapper, Validator validator) {
        this.contactRepository = contactRepository;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public Contact getContactById(Long contactId) {
        return getContact(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + contactId));
    }

    private Optional<Contact> getContact(Long contactId) {
        return contactRepository.findById(contactId);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public void createContact(ContactDto contactDto) {
        Contact contact = modelMapper.map(contactDto, Contact.class);
        validateContact(contact);
        contactRepository.save(contact);
    }

    @Override
    public void updateContact(Long id, ContactDto updatedContactDto) {
        Contact existingContact = getContactById(id);
        modelMapper.map(updatedContactDto, existingContact);
        validateContact(existingContact);
        contactRepository.save(existingContact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(getContactById(id).getId());
    }

    public void addContactToCompanies(Long contactId, List<Long> companyIds) {
        Contact existingContact = getContactById(contactId);
        List<Company> companies = companyIds.stream()
                .map(companyService::getCompanyById)
                .toList();
        existingContact.setCompanies(new HashSet<>(companies));
        contactRepository.save(existingContact);
    }

    private void validateContact(Contact contact) {
        Errors errors = new BeanPropertyBindingResult(contact, "contact");
        validator.validate(contact, errors);

        if (errors.hasErrors()) {
            throw new ContactValidationException("Freelance contacts must have a TVA number");
        }
    }
}
