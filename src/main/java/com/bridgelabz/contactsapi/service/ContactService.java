package com.bridgelabz.contactsapi.service;

import com.bridgelabz.contactsapi.dto.ContactDTO;
import com.bridgelabz.contactsapi.entity.ContactEntity;
import com.bridgelabz.contactsapi.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {
    private final ContactRepository contactRepository;

    // Constructor injection
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Save contact
    public ContactEntity saveContact(ContactDTO contactDTO) {
        ContactEntity contact = new ContactEntity();
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setAddress(contactDTO.getAddress());
        log.info("Saving contact: {}", contactDTO.getName());
        return contactRepository.save(contact);
    }

    // Get list of all contacts
    public List<ContactEntity> getAllContacts() {
        log.info("Fetching all contacts");
        return contactRepository.findAll();
    }

    // Get contact by ID
    public Optional<ContactEntity> getContactById(Long id) {
        try {
            log.info("Fetching contact with ID: {}", id);
            return contactRepository.findById(id);
        } catch (Exception e) {
            log.error("Error finding contact with ID: {}", id, e);
            return Optional.empty();
        }
    }

    // Delete contact by ID
    public boolean deleteContact(Long id) {
        try {
            contactRepository.deleteById(id);
            log.info("Deleted contact with ID: {}", id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting contact with ID: {}", id, e);
            return false;
        }
    }

    // Update contact
    public ContactEntity updateContact(Long id, ContactDTO contactDTO) {
        try {
            log.info("Updating contact with ID: {}", id);
            Optional<ContactEntity> optionalContact = contactRepository.findById(id);
            if (optionalContact.isPresent()) {
                ContactEntity existingContact = optionalContact.get();
                existingContact.setName(contactDTO.getName());
                existingContact.setEmail(contactDTO.getEmail());
                existingContact.setPhone(contactDTO.getPhone());
                existingContact.setAddress(contactDTO.getAddress());
                return contactRepository.save(existingContact);
            }
            return null;
        } catch (RuntimeException e) {
            log.error("An unexpected error occurred while updating contact with ID: {}", id, e);
            return null;
        }
    }
}
