package com.manjosh.expensetracker.app.service;

import com.manjosh.expensetracker.app.model.Task;
import com.manjosh.expensetracker.app.repository.TaskRepository;
import com.manjosh.expensetracker.app.service.iterfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(Task task) {
        taskRepository.insert(task);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        Task oldTask = taskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("No task with id %d is foung", task.getId())
                ));
        oldTask.setDay(task.getDay());
        oldTask.setRemainder(task.getRemainder());
        oldTask.setText(task.getText());
        taskRepository.save(oldTask);
        return task;
    }

    @Override
    public Task getTaskByName(String taskName) {
       return taskRepository.findByName(taskName).orElseThrow(() -> new RuntimeException(
                String.format("No task with name %s is foung", taskName)));
    }

    @Override
    public void deleteTaskById(String id) {
        taskRepository.deleteById(id);
    }
}
