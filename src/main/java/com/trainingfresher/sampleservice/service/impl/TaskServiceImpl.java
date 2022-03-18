package com.trainingfresher.sampleservice.service.impl;

import com.trainingfresher.sampleservice.api.form.TaskForm;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.model.entity.Task;
import com.trainingfresher.sampleservice.repository.ProjectRepository;
import com.trainingfresher.sampleservice.repository.SectionRepository;
import com.trainingfresher.sampleservice.repository.TaskRepository;
import com.trainingfresher.sampleservice.service.TaskService;
import com.trainingfresher.sampleservice.utils.constants.Constant;
import com.trainingfresher.sampleservice.utils.exception.BadRequestException;
import com.trainingfresher.sampleservice.utils.message.MessageConstants;
import com.trainingfresher.sampleservice.utils.message.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Nhat
 */

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SectionRepository sectionRepository;


    @Override
    public Task addNewTask(TaskForm dto)  {

        if (!StringUtils.hasText(dto.getName()) || !StringUtils.hasText(dto.getJobDescription())
                || !StringUtils.hasText(dto.getStatus())) {
            String msg = MessageUtils.getMessage(MessageConstants.INVALID_PARAMS);
            throw new BadRequestException(msg);
        }

        Task task = taskRepository.findByName(dto.getName());

        if (!ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_EXIST);
            throw new BadRequestException(msg);
        }

        Optional<Project> project = Optional.ofNullable(projectRepository.findById(dto.getProjectId()).get());
        Optional<Section> section = Optional.ofNullable(sectionRepository.findById(dto.getSectionId()).get());

        task = Task.builder()
                .name(dto.getName())
                .startDay(dto.getStartDay())
                .endDay(dto.getEndDay())
                .type(dto.getType())
                .jobDescription(dto.getJobDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .project(project.get())
                .section(section.get())
                .build();


        return save(task);
    }

    @Override
    public Task getById(Long id) {
        Task task = taskRepository.findById(id).get();
        if(ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new BadRequestException(msg);
        }

        return task;
    }

    @Override
    public List<Task> getListTask(Long projectId, Long sectionId) {
        List<Task> tasks;

        if(sectionId != null) {
            tasks = taskRepository.getListTaskInSection(projectId, sectionId);
        }else {
            tasks = taskRepository.getListTaskNonSection(projectId);
        }

        if(CollectionUtils.isEmpty(tasks)) {
            return Collections.EMPTY_LIST;
        }

        return tasks;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateStatus(Long id, String status) {

        Task task = taskRepository.findById(id).get();
        if(ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new BadRequestException(msg);
        }

        task.setStatus(status);
        return save(task);
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id).get();
        if(ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new BadRequestException(msg);
        }
        taskRepository.deleteById(id);

    }

        @Override
    public TaskDto convertToDto(Task task) {


            return TaskDto.builder()
                    .name(task.getName())
                    .startDay(task.getStartDay())
                    .endDay(task.getEndDay())
                    .type(task.getType())
                    .priority(task.getPriority())
                    .jobDescription(task.getJobDescription())
                    .status(task.getStatus())
                    .assignee(task.getAssignee().getName())
                    .projectName(task.getProjectName())
                    .section(task.getSection())
                    .project(task.getProject())
                    .comments(task.getComments())
                    .subTask(task.getSubTask())
                    .histories(task.getHistories())
                    .build();

        }
    }


