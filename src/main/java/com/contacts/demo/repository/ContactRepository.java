package com.contacts.demo.repository;

import com.contacts.demo.dao.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
