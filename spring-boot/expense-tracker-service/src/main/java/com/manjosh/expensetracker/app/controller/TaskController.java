package com.manjosh.expensetracker.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manjosh.expensetracker.app.model.Task;
import com.manjosh.expensetracker.app.service.iterfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService taskService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody String rowData) throws JsonProcessingException {
        Task task = objectMapper.readValue(rowData, Task.class);
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody String rowData) throws JsonProcessingException {
        Task task = objectMapper.readValue(rowData, Task.class);
        return ResponseEntity.ok(taskService.updateTask(task));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable  String id){
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{taskName}")
    public ResponseEntity<Task> getTaskByName(@PathVariable String taskName){
        return ResponseEntity.ok(taskService.getTaskByName(taskName));
    }

}
