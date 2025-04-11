package com.contacts.demo.controller;

import com.contacts.demo.dto.UserFormDto;
import com.contacts.demo.entity.User;
import com.contacts.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
	
	// 모든 유저 목록 출력
    @GetMapping("/all")
    public String getAllUsers(Model model) {
    	
    	List<User> users = userService.getAllUsers();
    	model.addAttribute("users", users);
    	
    	return "user/userList";
    }
    
    // 회원가입 폼 전송
    @GetMapping("/signup")
    public String userForm(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "user/signup";
    }
    
    // 회원가입 로직 처리
    @PostMapping("/signup")
    public String newMember(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model) {
    	
        if(bindingResult.hasErrors()) {
        	model.addAttribute("userFormDto", userFormDto);
        	return "user/signup";
        }
        
        
        try{
            userService.saveUser(userFormDto);
        } catch(IllegalStateException e) {
            model.addAttribute("errorMessage",e.getMessage());
            return "user/signup";
        }

        return "redirect:/login"; // 회원가입 완료 시 로그인 페이지로 이동	
    }
    
    @GetMapping("/login")
    public String loginUser(){
        return "user/loginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "user/loginForm";
    }
    
    @GetMapping("/logout")
    public String logout() {
    	
    	return "user/loginForm";
    }
	
}
