package com.trainingfresher.sampleservice.service.impl;

import com.trainingfresher.sampleservice.api.form.CommentForm;
import com.trainingfresher.sampleservice.api.form.TaskForm;
import com.trainingfresher.sampleservice.model.dto.TaskDto;
import com.trainingfresher.sampleservice.model.entity.Comment;
import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.model.entity.Task;
import com.trainingfresher.sampleservice.repository.CommentRepository;
import com.trainingfresher.sampleservice.repository.ProjectRepository;
import com.trainingfresher.sampleservice.repository.SectionRepository;
import com.trainingfresher.sampleservice.repository.TaskRepository;
import com.trainingfresher.sampleservice.service.TaskService;
import com.trainingfresher.sampleservice.utils.exception.BadRequestException;
import com.trainingfresher.sampleservice.utils.exception.NotFoundException;
import com.trainingfresher.sampleservice.utils.message.MessageConstants;
import com.trainingfresher.sampleservice.utils.message.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

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

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Task addNewTask(TaskForm dto) {

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

        Task parent = taskRepository.findById(dto.getParentId()).get();

        task = Task.builder()
                .name(dto.getName())
                .startDay(dto.getStartDay())
                .endDay(dto.getEndDay())
                .type(dto.getType())
                .jobDescription(dto.getJobDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .parent(dto.getParentId() != null ? parent : null)
                .project(project.get())
                .section(section.get())
                .build();

        if (!ObjectUtils.isEmpty(parent)) {
            parent.getSubTask().add(task);
            List<Task> tasks = Arrays.asList(task, parent);
            taskRepository.saveAll(tasks);
            return task;
        }

        return save(task);
    }

    @Override
    public Task getById(Long id) {
        Task task = taskRepository.findById(id).get();
        if (ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        return task;
    }

    @Override
    public List<Task> getListTask(Long projectId, Long sectionId) {
        List<Task> tasks;

        if (sectionId != null) {
            tasks = taskRepository.getListTaskInSection(projectId, sectionId);
        } else {
            tasks = taskRepository.getListTaskNonSection(projectId);
        }

        if (CollectionUtils.isEmpty(tasks)) {
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
        if (ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        task.setStatus(status);
        return save(task);
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id).get();
        if (ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new NotFoundException(msg);
        }
        taskRepository.deleteById(id);

    }

    @Override
    public Comment addComment(CommentForm dto, Long taskId) {

        Task task = taskRepository.findById(taskId).get();
        if (ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        if (!StringUtils.hasText(dto.getText())) {
            String msg = MessageUtils.getMessage(MessageConstants.INVALID_PARAMS);
            throw new BadRequestException(msg);
        }

        Comment parent = commentRepository.findById(dto.getParentId()).get();

        Comment comment = Comment.builder()
                .text(dto.getText())
                .date(new Date())
                .parentComment(dto.getParentId() != null ? parent : null)
                .build();
        if (!ObjectUtils.isEmpty(parent)) {
            parent.setSubComment(comment);
            List<Comment> comments = Arrays.asList(parent, comment);
            commentRepository.saveAll(comments);
            return comment;
        }

        return commentRepository.save(comment);


    }

    @Override
    public Comment editComment(Long taskId, Long commentId, String text) {
        Task task = taskRepository.findById(taskId).get();
        if (ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        Comment comment = commentRepository.findById(commentId).get();
        if (ObjectUtils.isEmpty(comment)) {
            String msg = MessageUtils.getMessage(MessageConstants.COMMENT_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        comment.setText(text);
        comment.setDate(new Date());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long taskId, Long commentId) {
        Task task = taskRepository.findById(taskId).get();
        if (ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        Comment comment = commentRepository.findById(commentId).get();
        if (ObjectUtils.isEmpty(comment)) {
            String msg = MessageUtils.getMessage(MessageConstants.COMMENT_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        commentRepository.deleteById(commentId);

    }

    @Override
    public List<Comment> getListComment(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        if (ObjectUtils.isEmpty(task)) {
            String msg = MessageUtils.getMessage(MessageConstants.TASK_NOT_EXIST);
            throw new NotFoundException(msg);
        }

        List<Comment> comments = commentRepository.findByTask(taskId);
        if(!CollectionUtils.isEmpty(comments)) {
            return comments;
        }else {
            return Collections.EMPTY_LIST;
        }

    }


}


