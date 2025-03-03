package com.bridgelabz.contactsapi.controller;


import com.bridgelabz.contactsapi.entity.ContactEntity;
import com.bridgelabz.contactsapi.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactEntity>> getAllContacts() {
        logger.info("Received request to get all contacts");
        List<ContactEntity> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactEntity> getContactById(@PathVariable Long id) {
        logger.info("Received request to get contact by ID: {}", id);
        Optional<ContactEntity> contact = contactService.getContactById(id);
        if (contact.isPresent()) {
            logger.info("Contact found: {}", contact.get());
            return ResponseEntity.ok(contact.get());
        } else {
            logger.warn("Contact with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContactEntity> addContact(@RequestBody ContactEntity contact) {
        logger.info("Received request to add new contact: {}", contact);
        ContactEntity savedContact = contactService.addContact(contact);
        return ResponseEntity.ok(savedContact);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactEntity> updateContact(@PathVariable Long id, @RequestBody ContactEntity contact) {
        logger.info("Received request to update contact with ID: {}", id);
        Optional<ContactEntity> updatedContact = contactService.updateContact(id, contact);
        if (updatedContact.isPresent()) {
            logger.info("Successfully updated contact with ID: {}", id);
            return ResponseEntity.ok(updatedContact.get());
        } else {
            logger.warn("Contact with ID: {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        logger.info("Received request to delete contact with ID: {}", id);
        if (contactService.deleteContact(id)) {
            logger.info("Successfully deleted contact with ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Contact with ID: {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}
