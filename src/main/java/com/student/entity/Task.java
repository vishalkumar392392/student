package com.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Task {

	@Id()
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "task")
	private String task;
}
