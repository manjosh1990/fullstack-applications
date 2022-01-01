package com.manjosh.expensetracker.app.service.iterfaces;

import com.manjosh.expensetracker.app.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task addTask(Task task);
    Task updateTask(Task task);
    Task getTaskByName(String taskName);
    void deleteTaskById(String id);
}
