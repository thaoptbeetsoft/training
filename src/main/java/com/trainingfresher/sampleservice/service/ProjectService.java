package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.api.form.ProjectForm;
import com.trainingfresher.sampleservice.model.dto.ProjectDto;
import com.trainingfresher.sampleservice.model.entity.Project;


import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Project> findById(Long id);
    Project save(Project project);
    void remove(Long id);
    List<Project> findAllByDepartmentId(Long id);
    boolean hide(Long id);
    boolean addProjectInDepartment(Long department_id,Project project);
    ProjectDto convertProjectToDto(Project project);
    Project convertFormToProject(ProjectForm projectForm);

    }
