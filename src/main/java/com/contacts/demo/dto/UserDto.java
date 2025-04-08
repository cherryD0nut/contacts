package com.contacts.demo.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserDto {

	private Long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private String role;
	
}