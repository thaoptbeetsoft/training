package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.api.form.CommentForm;
import com.trainingfresher.sampleservice.api.form.TaskForm;
import com.trainingfresher.sampleservice.api.response.ApiResponse;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import com.trainingfresher.sampleservice.model.entity.Comment;
import com.trainingfresher.sampleservice.model.entity.Task;
import com.trainingfresher.sampleservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<ApiResponse> addNewTask(@RequestBody TaskForm dto) {

        Task task = taskService.addNewTask(dto);
        TaskDto data = task.toDto();
        ApiResponse response = ApiResponse.appendSuccess(data, 200, "Add new task successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable(name = "id") Long id) {

        Task task = taskService.getById(id);
        TaskDto data = task.toDto();
        ApiResponse response = ApiResponse.appendSuccess(data, 200, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getTaskLists")
    public ResponseEntity<ApiResponse> getListTask(@RequestParam(name = "projectId") Long projectId, @RequestParam(name = "sectionId") Long sectionId) {

        List<Task> tasks = taskService.getListTask(projectId, sectionId);
        List<TaskDto> data = tasks.stream().map(Task::toDto).collect(Collectors.toList());
        ApiResponse response = ApiResponse.appendSuccess(data, 200, null);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable(name = "id") Long id ,@RequestParam(name = "status") String status) {

        Task task = taskService.updateStatus(id, status);
        TaskDto data = task.toDto();
        ApiResponse response = ApiResponse.appendSuccess(data, 200, "Update status successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable(name = "id") Long id) {
        taskService.delete(id);
        ApiResponse response = ApiResponse.appendSuccess(null, 200, "Delete status successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/addComment")
    public ResponseEntity<ApiResponse> addComment(@PathVariable(name = "id") Long taskId, @RequestBody CommentForm dto) {
        Comment data = taskService.addComment(dto, taskId);
        ApiResponse response = ApiResponse.appendSuccess(data, 200, "Add comment successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/getCommentLists")
    public ResponseEntity<ApiResponse> getListComment(@PathVariable(name = "id") Long taskId) {

        List<Comment>  data = taskService.getListComment(taskId);
        ApiResponse response = ApiResponse.appendSuccess(data, 200, null);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/{commentId}")
    public ResponseEntity<ApiResponse> editComment(@PathVariable(name = "id") Long taskId,
                                         @PathVariable(name = "commentId") Long commentId,
                                         @RequestParam(name = "text") String text) {
        Comment data = taskService.editComment(taskId, commentId, text);
        ApiResponse response = ApiResponse.appendSuccess(data, 200, "Edit comment successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "id") Long taskId,
                                         @PathVariable(name = "commentId") Long commentId) {
        taskService.deleteComment(taskId, commentId);
        ApiResponse response = ApiResponse.appendSuccess(null, 200,"Delete comment successfully");
        return ResponseEntity.ok(response);
    }
}
