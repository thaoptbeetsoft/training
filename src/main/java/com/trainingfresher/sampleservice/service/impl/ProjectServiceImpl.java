package com.trainingfresher.sampleservice.service.impl;

import com.trainingfresher.sampleservice.api.form.ProjectForm;
import com.trainingfresher.sampleservice.model.dto.ProjectDto;
import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.repository.ProjectRepository;
import com.trainingfresher.sampleservice.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public List<Project> findAllByDepartmentId(Long id) {
        return projectRepository.findAllByDepartmentId(id);

    }

    @Override
    public boolean hide(Long id) {
        Optional<Project>projectOptional = projectRepository.findById(id);
        if(!projectOptional.isPresent()){
            return false;
        }
        projectOptional.get().setEnable(false);
        projectRepository.save(projectOptional.get());
        return true;
    }

    @Override
    public boolean addProjectInDepartment(Long departmentId,Project project) {
       Optional<Department> department= departmentService.findById(departmentId);
       if(!department.isPresent()){
           return false;
       }
       Project projectNew = projectRepository.save(project);
       List<Department>departments = new ArrayList<>();
        departments.add(department.get());
        projectNew.setDepartments(departments);
        projectRepository.save(projectNew);
        return true;
    }

    @Override
    public ProjectDto convertProjectToDto(Project project){
        ProjectDto projectDto = modelMapper.map(project,ProjectDto.class);
        return projectDto;
    }

    @Override
    public Project convertFormToProject(ProjectForm projectForm) {
        Project project = modelMapper.map(projectForm,Project.class);
        return project;
    }
}
