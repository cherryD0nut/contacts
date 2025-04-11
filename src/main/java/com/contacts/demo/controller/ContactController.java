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
    public String deleteContact(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        try {
            contactService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "삭제 중 에러가 발생했습니다.");
        }
        return "redirect:/contacts";
    }

    @PostMapping("/contacts/add")
    public String addContact(@Valid @ModelAttribute("contactDto") ContactDto contactDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactDto", bindingResult);
            redirectAttributes.addFlashAttribute("contactDto", contactDto);
            redirectAttributes.addFlashAttribute("openModal", true);
            return "redirect:/contacts";
        }

        try {
            contactService.saveContact(contactDto);
            redirectAttributes.addFlashAttribute("message", "연락처가 추가되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "생성에 실패했습니다.");
        }
        return "redirect:/contacts";
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