package com.trainingfresher.sampleservice.service.project;

import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.repository.ProjectRepository;
import com.trainingfresher.sampleservice.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Section> findAll() {
        return null;
    }

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
    public boolean addProjectInDepartment(Long departmentId, Long projectId) {
       Optional<Department> department= departmentService.findById(departmentId);
       if(!department.isPresent()){
           return false;
       }
       Optional<Project>projectOptional = projectRepository.findById(projectId);
        if(!projectOptional.isPresent()){
            return false;
        }
        List<Department>list = new ArrayList<>();
        list.add(department.get());
       projectOptional.get().setDepartments(list);
       projectRepository.save(projectOptional.get());
        return true;
    }
}
