package com.bridgelabz.contactsapi.controller;

import com.bridgelabz.contactsapi.dto.ContactDTO;
import com.bridgelabz.contactsapi.entity.ContactEntity;
import com.bridgelabz.contactsapi.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    public ContactService contactService;
    // Get all contacts
    @GetMapping
    public ResponseEntity<List<ContactEntity>> getAllContacts() {
        log.info("Received request to get all contacts");
        List<ContactEntity> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }
    // Get contact by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ContactEntity> getContactById(@PathVariable Long id) {
        log.info("Received request to get contact by ID: {}", id);
        Optional<ContactEntity> contact = contactService.getContactById(id);
        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get());
        } else {
            log.warn("Contact with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
    // Add a new contact
    @PostMapping
    public ResponseEntity<ContactEntity> addContact(@RequestBody ContactDTO contactDTO) {
        log.info("Received request to add new contact: {}", contactDTO);
        ContactEntity savedContact = contactService.saveContact(contactDTO);
        if (savedContact != null) {
            return ResponseEntity.ok(savedContact);
        } else {
            log.error("Error saving contact: {}", contactDTO);
            return ResponseEntity.badRequest().build();
        }
    }
    // Update contact
    @PutMapping("/update/{id}")
    public ResponseEntity<ContactEntity> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        log.info("Received request to update contact with ID: {}", id);
        ContactEntity updatedContact = contactService.updateContact(id, contactDTO);
        if (updatedContact != null) {
            return ResponseEntity.ok(updatedContact);
        } else {
            log.warn("Contact with ID: {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
    }
    // Delete contact
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.info("Received request to delete contact with ID: {}", id);
        boolean isDeleted = contactService.deleteContact(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Contact with ID: {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}
