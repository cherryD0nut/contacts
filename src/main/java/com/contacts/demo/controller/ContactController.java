package com.contacts.demo.controller;

import com.contacts.demo.dto.ContactDto;
import com.contacts.demo.service.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contacts")
    public String getContacts(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<ContactDto> contactPage = contactService.getAllContacts(pageable);

        model.addAttribute("contacts", contactPage);

        // 수정된 부분: contactDto가 없으면 빈 객체로 추가
        if (!model.containsAttribute("contactDto")) {
            model.addAttribute("contactDto", new ContactDto());
        }

        return "contactList";
    }

    @PostMapping("/contacts/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteContact(@PathVariable(name = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            contactService.deleteById(id);
            response.put("success", true);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PostMapping("/contacts/add")
    @ResponseBody
    public Map<String, Object> addContact(@Valid @ModelAttribute("contactDto") ContactDto contactDto,
                             BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        try {
            contactService.saveContact(contactDto);
            response.put("success", true);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

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