package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Task;
import com.student.repository.TaskRepository;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping(value = "/addTask")
	public Task addTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@GetMapping(value = "/getTasks")
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	

}
