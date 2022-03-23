package com.trainingfresher.sampleservice.model.dto;

import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.model.entity.Task;
import com.trainingfresher.sampleservice.model.entity.Team;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
    private String name;
    private boolean enable;
    private List<Task>tasks;
    private List<Section>sections;
    private List<Department>departments;
    private List<Team>teams;
}
