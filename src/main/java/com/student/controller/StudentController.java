package com.student.controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.entity.Student;
import com.student.entity.Task;
import com.student.model.FormData;
import com.student.model.LoginDetails;
import com.student.repository.TaskRepository;
import com.student.security.AES;
import com.student.service.StudentService;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private AES aes;

	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {
		return studentService.save(student);
	}

	@PostMapping("/form")
	public String form(@ModelAttribute FormData formData) {

		return formData.getFile().getOriginalFilename();
	}

	@PostMapping("/token")
	public String getToken(@RequestBody LoginDetails loginDetails)
			throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		ObjectMapper mapper = new ObjectMapper();
		return aes.encrypt(mapper.writeValueAsString(loginDetails));
	}

	@GetMapping(value = "/decrypt", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDetails(@RequestHeader(name = "Authorization") String authorization)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return aes.decrypt(authorization);
	}
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping(name = "/addTask")
	public Task addTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@GetMapping(name = "/getTasks")
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}

}
