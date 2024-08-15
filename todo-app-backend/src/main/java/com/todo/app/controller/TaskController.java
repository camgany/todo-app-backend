package com.todo.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.model.Task;
import com.todo.app.repo.TaskRepo;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class TaskController {

    @Autowired
    private TaskRepo repo;

    @GetMapping("/tasks")
    public List<Task> listAllTasks() {
        return repo.findAll();
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task) {
        Task createdTask = repo.save(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskId(@PathVariable Long id) {
        Optional<Task> task = repo.findById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTaskId(@PathVariable Long id, @Valid @RequestBody Task dataTask) {
        Optional<Task> task = repo.findById(id);
        if (task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setName_task(dataTask.getName_task());
            existingTask.setDescription(dataTask.getDescription());
            existingTask.setStatus(dataTask.getStatus());

            Task taskUpdated = repo.save(existingTask);
            return ResponseEntity.ok(taskUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> task = repo.findById(id);
        if (task.isPresent()) {
            repo.delete(task.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
