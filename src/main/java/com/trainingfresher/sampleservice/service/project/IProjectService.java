package com.trainingfresher.sampleservice.service.project;

import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.service.IGeneralService;

import java.util.List;

public interface IProjectService extends IGeneralService<Project> {
    List<Project> findAllByDepartmentId(Long id);
    boolean hide(Long id);
    boolean addProjectInDepartment(Long department_id,Long project_id);

}
