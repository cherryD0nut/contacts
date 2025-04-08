package com.contacts.demo.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class UserFormDto {
	
	// id는 자동 설정, role = user 로 자동설정
	
    @NotBlank(message="id를 입력하세요.")
    private String username;
	
    @NotEmpty(message="비밀번호를 입력하세요.")
    @Length(min=8, message="비밀번호는 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message="이름을 입력하세요.")
    private String name;

    @NotEmpty(message="이메일을 입력하세요.")
    @Email(message="올바른 이메일 형식이 아닙니다.")
    private String email;


}
