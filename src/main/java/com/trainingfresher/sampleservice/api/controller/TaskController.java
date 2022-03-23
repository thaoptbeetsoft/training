package com.trainingfresher.sampleservice.api.controller;
import com.trainingfresher.sampleservice.api.form.CommentForm;
import com.trainingfresher.sampleservice.api.form.TaskForm;
import com.trainingfresher.sampleservice.api.response.ApiResponse;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import com.trainingfresher.sampleservice.model.entity.Comment;
import com.trainingfresher.sampleservice.model.entity.Task;
import com.trainingfresher.sampleservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<ApiResponse> addNewTask(@RequestBody TaskForm _dto) {

        Task task = taskService.addNewTask(_dto);
        TaskDto data = task.toDto();
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.CREATED.value(), "Add new task successfully")
                                            : ApiResponse.appendError( HttpStatus.NO_CONTENT.value(), "Error");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable(name = "id") Long _id) {

        Task task = taskService.getById(_id);
        TaskDto data = task.toDto();
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.OK.value(), null)
                                            : ApiResponse.appendError( HttpStatus.NO_CONTENT.value(), "Error");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getTaskList")
    public ResponseEntity<ApiResponse> getListTask(@RequestParam(name = "projectId", required = false) Long _projectId, @RequestParam(name = "sectionId", required = false) Long _sectionId) {

        List<Task> tasks = taskService.getListTask(_projectId,_sectionId);
        List<TaskDto> data = tasks.stream().map(Task::toDto).collect(Collectors.toList());
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.OK.value(), null)
                                            : ApiResponse.appendError( HttpStatus.NO_CONTENT.value(), "Error");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable(name = "id") Long _id ,@RequestParam(name = "status") String _status) {

        Task task = taskService.updateStatus(_id, _status);
        TaskDto data = task.toDto();
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.OK.value(), "Update status task successfully")
                                            : ApiResponse.appendError(HttpStatus.BAD_REQUEST.value(), "Error");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable(name = "id") Long _id) {
        taskService.delete(_id);
        ApiResponse response = ApiResponse.appendSuccess(null, HttpStatus.OK.value(), "Delete task successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/addComment")
    public ResponseEntity<ApiResponse> addComment(@PathVariable(name = "id") Long _taskId, @RequestBody CommentForm _dto) {
        Comment data = taskService.addComment(_dto, _taskId);
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.CREATED.value(), "Add new comment successfully")
                                            : ApiResponse.appendError( HttpStatus.NO_CONTENT.value(), "Error");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/getCommentList")
    public ResponseEntity<ApiResponse> getListComment(@PathVariable(name = "id") Long _taskId) {

        List<Comment> data = taskService.getListComment(_taskId);
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.OK.value(), null)
                                            : ApiResponse.appendError( HttpStatus.NO_CONTENT.value(), "Error");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/{commentId}")
    public ResponseEntity<ApiResponse> editComment(@PathVariable(name = "id") Long _taskId,
                                                   @PathVariable(name = "commentId") Long _commentId,
                                                   @RequestBody CommentForm _commentForm) {
        Comment data = taskService.editComment(_taskId, _commentId, _commentForm.getText());
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.OK.value(), "Edit comment successfully")
                                            : ApiResponse.appendError(HttpStatus.BAD_REQUEST.value(), "Error");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "id") Long _taskId,
                                                     @PathVariable(name = "commentId") Long _commentId) {
        taskService.deleteComment(_taskId, _commentId);
        ApiResponse response = ApiResponse.appendSuccess(null, HttpStatus.OK.value(),"Delete comment successfully");
        return ResponseEntity.ok(response);
    }
}
