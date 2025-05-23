package com.contacts.demo.service;

import com.contacts.demo.entity.ProfileImg;
import com.contacts.demo.entity.User;
import com.contacts.demo.repository.ProfileImgRepository;
import com.contacts.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

    private final ProfileImgRepository profileImgRepository;
    private final UserRepository userRepository;
    private final UserDetailService userDetailService;

    public void saveProfileFile(MultipartFile file, String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        // 파일 저장 경로
        String uploadDir = "D:\\upload\\";

        // 원본 파일명 변환
        String originalName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String extension = originalName.substring(originalName.lastIndexOf("."));

        String fileName = uuid + extension;
        System.out.println("fileName: " + fileName);

        String fullPath = uploadDir + fileName;
        File dir = new File(fullPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(dir);
        } catch (IOException e) {
            System.out.println("지정 경로를 찾을 수 없습니다.");
            throw new RuntimeException(e);
        }

        ProfileImg profileImg = ProfileImg.builder()
                .user(user)
                .fileName(fileName)
                .filePath(fullPath)
                .fileSize(file.getSize())
                .mimeType(extension).build();


        profileImgRepository.save(profileImg);

    }
}
