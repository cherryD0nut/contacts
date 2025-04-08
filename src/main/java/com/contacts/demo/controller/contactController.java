package com.contacts.demo.controller;
import com.contacts.demo.dao.Contact;
import com.contacts.demo.dto.ContactDto;
import com.contacts.demo.service.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class contactController {

    private final ContactService contactService;


    // 모든 연락처 출력
    @GetMapping("/contacts")
    public String getContacts(Model model){
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts",contacts);
        model.addAttribute("contactDto", new ContactDto());
        return "contactList";
    }

    // 연락처 삭제
    @PostMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable(name="id") Long id, Model model) {

        try {
            contactService.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("message", "삭제 중 에러가 발생하였습니다.");
        }
        return "redirect:/contacts";

    }

    // 연락처 생성
    @PostMapping("/contacts/add")
    public String addContact(@Valid @ModelAttribute ContactDto contactDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {

    	System.out.println(contactDto.getName());
    	System.out.println(contactDto.getEmail());
    	System.out.println(contactDto.getPhoneNo());
    	
        if (bindingResult.hasErrors()) {
            // 에러 메시지 다시 보여주고 모달 유지
            model.addAttribute("contacts", contactService.getAllContacts());
            model.addAttribute("openModal", true); // 모달 다시 열기 위한 플래그
            return "contacts";
        }

        try{
            contactService.saveContact(contactDto);
            redirectAttributes.addFlashAttribute("message", "연락처가 추가되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "생성에 실패했습니다.");
        }
        return "redirect:/contacts";
    }

    // 연락처 수정
    @PostMapping("/contacts/update")
    public String updateContact(@ModelAttribute ContactDto contactDto,
                                RedirectAttributes redirectAttributes) {
        try {
            contactService.updateContact(contactDto);
            redirectAttributes.addFlashAttribute("message", "연락처가 수정되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "수정에 실패했습니다.");
        }
        return "redirect:/contacts";
    }
}