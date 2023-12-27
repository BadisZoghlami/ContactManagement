package com.contact.management.controller;

import com.contact.management.model.Contact;
import com.contact.management.model.ContactDto;
import com.contact.management.model.ContactToCompaniesRequest;
import com.contact.management.service.ContactService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
@Validated
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();

        List<ContactDto> contactDtos = contacts.stream()
                .map(contact -> modelMapper.map(contact, ContactDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(contactDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable Long id) {

        ContactDto contactDto = modelMapper.map(contactService.getContactById(id), ContactDto.class);
        return new ResponseEntity<>(contactDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody @Valid ContactDto contact) {
        contactService.createContact(contact);
        return new ResponseEntity<>("Contact created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Long id, @RequestBody ContactDto updatedContact) {
        contactService.updateContact(id, updatedContact);
        return new ResponseEntity<>("Contact updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>("Contact deleted", HttpStatus.OK);
    }

    @PostMapping("/addToCompany")
    public ResponseEntity<String> addContactToCompany(@RequestBody ContactToCompaniesRequest request) {
        contactService.addContactToCompanies(request.getContactId(), request.getCompanyIds());
        return new ResponseEntity<>("Contact added to company", HttpStatus.OK);
    }

}
