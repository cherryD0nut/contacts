package com.contacts.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profile")
public class FileController {
	
	
	// 파일 저장 경로
    private final String uploadDir = "C:\\Users\\judi0\\Desktop\\Projects\\upload\\";

    @GetMapping("/upload")
    public String upload() {
        return "profile";
    }

    @PostMapping("/upload")
    public String form(@RequestParam("photoInput") MultipartFile file) throws IOException {

        // 원본 파일명과 저장할 파일명
        String originalName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");


        try {
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String fileName = uuid + extension;
            System.out.println("fileName: " + fileName);

            if (!file.isEmpty()) {
                String fullPath = uploadDir + fileName;
                file.transferTo(new File(fullPath));

                System.out.println(fullPath);
            }

        } catch (NullPointerException e) {
            throw new NullPointerException("No such file or directory");
        }

        return "profile";
    }

}
