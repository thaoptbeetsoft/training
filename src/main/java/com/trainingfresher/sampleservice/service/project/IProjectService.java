package com.trainingfresher.sampleservice.service.project;

import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.service.IGeneralService;

public interface IProjectService extends IGeneralService<Project> {
    Iterable<Project> findAllByDepartment_id(Long id);
    Iterable<Project> findAllByUser_IdEndDepartment_id(Long id);
    boolean hide(Long id);
}
