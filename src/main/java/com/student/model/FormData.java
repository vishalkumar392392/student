package com.student.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FormData {
	
	private String fname;
	
	private String lname;
	
	private String email;
	
	private String number;
	
	private String address;
	
	private MultipartFile file;

}
