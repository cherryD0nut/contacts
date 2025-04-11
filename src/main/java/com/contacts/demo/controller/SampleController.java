package com.contacts.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contacts.demo.entity.User;

@Controller
public class SampleController {
	
	@GetMapping("/")
	public String getSampleData(Model model) {
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            model.addAttribute("name", user.getName());
        }
        
        return "index";
	}
}
