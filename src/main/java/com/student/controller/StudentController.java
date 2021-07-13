package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {
		return studentService.save(student);
	}
	

}
