package com.contacts.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactDto {

    private Long id;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수입니다.")
    private int phoneNo;


}
