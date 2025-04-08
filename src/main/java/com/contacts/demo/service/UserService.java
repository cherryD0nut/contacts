package com.contacts.demo.service;


import lombok.RequiredArgsConstructor;
import com.contacts.demo.entity.User;
import com.contacts.demo.dto.UserFormDto;
import com.contacts.demo.repository.UserRepository;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    // 회원가입 시 id 중복 확인
    private void validateDuplicateMember(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // 회원가입 로직
    public Long saveUser(UserFormDto userFormDto) {
        validateDuplicateMember(userFormDto.getUsername());
        
        return userRepository.save(User.builder()
        		.username(userFormDto.getUsername())
        		.password(bCryptPasswordEncoder.encode(userFormDto.getPassword()))
        		.name(userFormDto.getName())
        		.email(userFormDto.getEmail())
        				.build()).getId();
    }
    
    // 모든 유저 출력
    public List<User> getAllUsers() {
    	return userRepository.findAll();
    }
    
}