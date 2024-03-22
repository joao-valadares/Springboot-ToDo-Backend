package com.example.todoapp.Services;

import com.example.todoapp.DTOS.TaskDto;
import com.example.todoapp.Models.TaskModel;
import com.example.todoapp.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<Object> postTask(TaskDto taskDto){
        TaskModel task = TaskModel.builder()
                    .content(taskDto.content())
                    .completed(taskDto.completed()).build();

        taskRepository.save(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    public ResponseEntity<List<TaskModel>> getAllTasks(){
        return ResponseEntity
                .status(HttpStatus.OK)
                        .body(taskRepository.findAll());
    }

    public ResponseEntity<Object> getTaskById(Long id){

        if(taskRepository.findById(id).isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Wanis é viado");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskRepository.findById(id));

    }

    public ResponseEntity<Object> patchTask(Long id, TaskDto taskDto){
        Optional<TaskModel> task = taskRepository.findById(id);
        if(task.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error: Not Found");
        }

        task.get().setCompleted(taskDto.completed());

        TaskModel taskCompleted = task.get();
        taskRepository.save(taskCompleted);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(task);
    }

    public ResponseEntity<Object> deleteAllTasks(){
        taskRepository.deleteAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("All tasks Deleted");
    }

    public ResponseEntity<Object> deleteTaskById(Long id){
        if(taskRepository.findById(id).isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error: Not Found");
        }

        taskRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Task " + id + " Deleted");
    }





}
