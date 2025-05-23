package com.contacts.demo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileImgDto {

    private Long id;

    private String fileName;

    private String filePath;

    private String fileSize;

    private String mime_type;

}
