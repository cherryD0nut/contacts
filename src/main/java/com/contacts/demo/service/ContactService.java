package com.contacts.demo.service;


import com.contacts.demo.entity.Contact;
import com.contacts.demo.dto.ContactDto;
import com.contacts.demo.repository.ContactRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactService {


    private final ContactRepository contactRepository;

    // 모든 연락처 출력
    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    // 연락처 삭제
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    // 연락처 추가
    public void saveContact(ContactDto contactDto) throws Exception{
        Contact contact = new Contact();
        
        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhoneNo(contactDto.getPhoneNo());
        
        contactRepository.save(contact);

    }
    
    // 연락처 수정
    public void updateContact(ContactDto contactDto) {
        Optional<Contact> optionalContact = contactRepository.findById(contactDto.getId());
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            
            // 이름은 수정 불가라서 건드리지 않음
            contact.setEmail(contactDto.getEmail());
            contact.setPhoneNo(contactDto.getPhoneNo());

            contactRepository.save(contact);
        } else {
            throw new RuntimeException("연락처를 찾을 수 없습니다. ID: " + contactDto.getId());
        }
    }
    
}
