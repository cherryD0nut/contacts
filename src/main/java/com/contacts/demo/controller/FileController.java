package com.contacts.demo.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import com.contacts.demo.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class FileController {
	
    private final FileService fileService;

    @GetMapping("/")
    public String upload(Principal principal) {
        System.out.println("principal name: " + principal.getName());

        return "profile";
    }

    @PostMapping("/upload")
    public String form(@RequestParam("photoInput") MultipartFile file, Principal principal) throws IOException {
        String username = principal.getName();
        if (!file.isEmpty()) {
            fileService.saveProfileFile(file, username);
        }
        return "profile";
    }

    @GetMapping("/delete")
    public String delete(Principal principal) {
        System.out.println("principal name: " + principal.getName());


        return "profile";
    }
}
