package com.example.todo_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app.domain.Task;
import com.example.todo_app.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.delete(taskId);
    }
}
