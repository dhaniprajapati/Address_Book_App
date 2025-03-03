package com.bridgelabz.contactsapi.service;


import com.bridgelabz.contactsapi.entity.ContactEntity;
import com.bridgelabz.contactsapi.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private ContactRepository contactRepository;
    //get list of contacts
    public List<ContactEntity> getAllContacts() {
        logger.info("Fetching all contacts");
        return contactRepository.findAll();
    }
    //grt contact by id
    public Optional<ContactEntity> getContactById(Long id) {
        logger.info("Fetching contact with ID: {}", id);
        return contactRepository.findById(id);
    }
    //add contact
    public ContactEntity addContact(ContactEntity contact) {
        logger.info("Adding new contact: {}", contact);
        return contactRepository.save(contact);
    }
    //update contact details
    public Optional<ContactEntity> updateContact(Long id, ContactEntity newContact) {
        logger.info("Updating contact with ID: {}", id);
        Optional<ContactEntity> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            ContactEntity contact = existingContact.get();
            contact.setName(newContact.getName());
            contact.setEmail(newContact.getEmail());
            contact.setPhone(newContact.getPhone());
            contact.setAddress(newContact.getAddress());
            contactRepository.save(contact);
            logger.info("Updated contact with ID: {}", id);
            return Optional.of(contact);
        } else {
            logger.warn("Contact with ID: {} not found", id);
            return Optional.empty();
        }
    }
    //delete contact
    public boolean deleteContact(Long id) {
        logger.info("Deleting contact with ID: {}", id);
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            logger.info("Deleted contact with ID: {}", id);
            return true;
        }
        logger.warn("Contact with ID: {} not found", id);
        return false;
    }
}