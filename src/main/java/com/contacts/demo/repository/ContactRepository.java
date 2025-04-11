package com.contacts.demo.repository;

import com.contacts.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Page<Contact> findAll(Pageable pageable);

}
