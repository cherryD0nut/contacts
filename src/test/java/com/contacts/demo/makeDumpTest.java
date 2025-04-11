package com.contacts.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.contacts.demo.dto.ContactDto;
import com.contacts.demo.service.ContactService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(true)
public class makeDumpTest {
	
    @Autowired
    private ContactService contactService;

    @Test
    void makeDump() {
    	for(int i=1; i<200; i++) {
    		
    		ContactDto contactDto = new ContactDto(); 
    		
    		contactDto.setEmail("user" + i + "0@gmail.com");
    		contactDto.setName("user" + i + "0");
    		contactDto.setPhoneNo("01011111111");
    		
    		try{
        		contactService.saveContact(contactDto);    			
    		}catch(Exception e) {
    			System.out.println("덤프 데이터 생성 불가: " + e);
    		}
    		

    	}
    }

}
