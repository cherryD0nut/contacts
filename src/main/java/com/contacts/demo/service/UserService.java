package com.contacts.demo.service;

import com.contacts.demo.mapper.UserMapper;
import com.contacts.demo.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
    public List<UserDto> getAllUsers() {
        return userMapper.findAll();
    }

    public UserDto getUserById(String id) {
        return userMapper.findById(id);
    }

    public void createUser(UserDto userDto) {
        userMapper.insert(userDto);
    }

    public void updateUser(UserDto userDto) {
        userMapper.update(userDto);
    }

    public void deleteUser(String id) {
        userMapper.delete(id);
    }
    

    

	
}
