package com.todo.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.app.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{

}
