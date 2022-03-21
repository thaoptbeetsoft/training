package com.trainingfresher.sampleservice.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.model.entity.Team;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class DepartmentDto implements Serializable {
    private long id;
    private String name;
    private List<Team> teams;
    private List<Project> projects;
}
