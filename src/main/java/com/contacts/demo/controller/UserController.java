package com.contacts.demo.controller;

import com.contacts.demo.dto.UserDto;
import com.contacts.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
    	List<UserDto> users = userService.getAllUsers();
    	model.addAttribute("users", users);
        return "user/userList";
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("/getName")
    public String getNamePage() {
    	return "getName";
    }
    
	@GetMapping("/getName/data")
	@ResponseBody
    /* inputUsername 파라미터를 받아, DB에서 해당 username에 맵핑된 name을 리턴해주는 메소드 */
	public Map<String,Object> getName( @RequestParam String inputUsername ) {
		
		UserDto user = userService.getUserById(inputUsername); 
		String name = user.getName();
	    
		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("username", inputUsername);
		returnMap.put("name", name);
		
		return returnMap;
	}
	
}
