package com.contacts.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping("/")
	public String getSampleData(Model model) {
		model.addAttribute("name", "조유연");
		return "index"; 
	}
}
