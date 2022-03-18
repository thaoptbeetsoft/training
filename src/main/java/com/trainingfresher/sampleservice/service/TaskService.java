package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.api.form.TaskForm;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import com.trainingfresher.sampleservice.model.entity.Task;
import com.trainingfresher.sampleservice.utils.exception.BadRequestException;

import java.util.List;

/**
 * @author Nhat
 */

public interface TaskService {
    Task addNewTask(TaskForm dto);
    Task save(Task task);

    Task updateStatus(Long id, String status);

    void delete(Long id);

    Task getById(Long id);

    List<Task> getListTask(Long projectId, Long sectionId);

    TaskDto convertToDto(Task task);
}
