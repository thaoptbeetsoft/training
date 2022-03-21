package com.trainingfresher.sampleservice.api.form;

import lombok.Data;

import java.util.Date;

@Data
public class TaskForm {

    private String name;
    private Date startDay;
    private Date endDay;
    private String priority;
    private String jobDescription;
    private String status;
    private Long parentId;
    private Long projectId;
    private Long sectionId;
}
