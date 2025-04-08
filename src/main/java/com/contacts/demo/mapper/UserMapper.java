package com.contacts.demo.mapper;

import com.contacts.demo.dto.UserDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<UserDto> findAll();

    @Select("SELECT * FROM users WHERE username = #{username}")
    UserDto findById(String username);

    @Insert("INSERT INTO users (username, password, name, email) VALUES (#{username}, #{password}, #{name}, #{email})")
    void insert(UserDto userDto);

    @Update("UPDATE users SET email = #{email} WHERE username = #{username}")
    void update(UserDto userDto);

    @Delete("DELETE FROM users WHERE username = #{username}")
    void delete(String username);
	
}
