package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
