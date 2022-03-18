package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.api.form.TaskForm;
import com.trainingfresher.sampleservice.api.response.ApiResponse;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import com.trainingfresher.sampleservice.model.entity.Task;
import com.trainingfresher.sampleservice.service.TaskService;
import com.trainingfresher.sampleservice.utils.message.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nhat
 */

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewTask(@RequestBody TaskForm dto) {

        Task task = taskService.addNewTask(dto);
        TaskDto data = taskService.convertToDto(task);
        ApiResponse response = ApiResponse.appendSuccess(data, 200, MessageConstants.ADD_NEW_TASK_SUCCESS);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable(name = "id") Long id) {

        Task task = taskService.getById(id);
        TaskDto data = taskService.convertToDto(task);
        ApiResponse response = ApiResponse.appendSuccess(data, 200, null);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/getListTask")
    public ResponseEntity<?> getListTask(@RequestParam(name = "project_id") Long projectId, @RequestParam(name = "section_id") Long sectionId) {

        List<Task> tasks = taskService.getListTask(projectId, sectionId);
        List<TaskDto> data = tasks.stream().map(item -> {
            TaskDto task = taskService.convertToDto(item);
            return task;
        }).collect(Collectors.toList());

        ApiResponse response = ApiResponse.appendSuccess(data, 200, null);
        return ResponseEntity.ok(response);

    }


    @PatchMapping("/{id}/updateStatus")
    public ResponseEntity<?> updateStatus(@PathVariable(name = "id") Long id ,@RequestParam(name = "status") String status) {

        Task task = taskService.updateStatus(id, status);
        TaskDto data = taskService.convertToDto(task);
        ApiResponse response = ApiResponse.appendSuccess(data, 200, MessageConstants.UPDATE_STATUS_SUCCESS);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long id) {
        taskService.delete(id);
        ApiResponse response = ApiResponse.appendSuccess(null, 200, MessageConstants.DELETE_TASK_SUCCESS);
        return ResponseEntity.ok(response);
    }



}
