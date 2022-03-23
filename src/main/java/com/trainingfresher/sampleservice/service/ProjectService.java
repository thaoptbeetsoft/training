package com.trainingfresher.sampleservice.service;

import com.trainingfresher.sampleservice.model.entity.Project;


import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Project> findById(Long id);
    Project save(Project project);
    void remove(Long id);
    List<Project> findAllByDepartmentId(Long id);
    boolean hide(Long id);
    boolean addProjectInDepartment(Long department_id,Long project_id);

}
