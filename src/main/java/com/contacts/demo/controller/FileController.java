package com.contacts.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	
	// 파일 저장 경로
    private final String uploadDir = "C:\\Users\\nigos";

    @GetMapping("/upload")
    public String upload() {
        return "uploadFile";
    }

    @PostMapping("/upload")
    public String form(@RequestParam String fileName,
                       @RequestParam MultipartFile file) throws IOException {

    	System.out.println("fileName: " + fileName);
    	
        if (!file.isEmpty()) {
            String filename = fileName;

            String fullPath = uploadDir + filename;
            file.transferTo(new File(fullPath));
        }
        return "uploadFile";
    }

}
