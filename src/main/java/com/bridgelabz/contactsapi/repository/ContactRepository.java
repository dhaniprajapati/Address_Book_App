package com.bridgelabz.contactsapi.repository;

import com.bridgelabz.contactsapi.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
