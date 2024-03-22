package com.example.todoapp.Controllers;


import com.example.todoapp.DTOS.TaskDto;
import com.example.todoapp.Models.TaskModel;
import com.example.todoapp.Services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskModel>> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "id") Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> postTask(@RequestBody TaskDto taskDto){
        return taskService.postTask(taskDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> putTask(@RequestBody TaskDto taskDto, @PathVariable(value = "id") Long id){
        return taskService.patchTask(id, taskDto);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAllTasks(){
        return taskService.deleteAllTasks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id){
        return taskService.deleteTaskById(id);
    }

}
