package com.trainingfresher.sampleservice.model.dto;

import com.trainingfresher.sampleservice.model.entity.*;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDto{
    private String name;
    private boolean enable;
    private List<Task>tasks;
    private List<Section>sections;
    private List<Department>departments;
    private List<Team>teams;


}
