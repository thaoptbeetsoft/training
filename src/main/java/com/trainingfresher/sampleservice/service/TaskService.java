package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.api.form.CommentForm;
import com.trainingfresher.sampleservice.api.form.TaskForm;
import com.trainingfresher.sampleservice.model.entity.Comment;
import com.trainingfresher.sampleservice.model.entity.Task;

import java.util.List;

public interface TaskService {
    Task addNewTask(TaskForm dto);

    Task save(Task task);

    Task updateStatus(Long id, String status);

    void delete(Long id);

    Task getById(Long id);

    List<Task> getListTask(Long projectId, Long sectionId);

    Comment addComment(CommentForm dto, Long taskId);

    Comment editComment(Long taskId, Long commentId, String text);

    void deleteComment(Long taskId, Long commentId);

    List<Comment> getListComment(Long taskId);
}
