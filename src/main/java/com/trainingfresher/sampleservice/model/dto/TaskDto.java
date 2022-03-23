package com.trainingfresher.sampleservice.model.dto;

import com.trainingfresher.sampleservice.model.entity.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@Builder
public class TaskDto {

    private String name;
    private Date startDay;
    private Date endDay;
    private String type;
    private String priority;
    private String jobDescription;
    private String status;
    private String projectName;
    private String assignee;
    private Section section;
    private Project project;
    private List<Comment> comments;
    private List<Task> subTask;
    private List<History> histories;




}
