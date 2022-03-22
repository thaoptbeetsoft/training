package com.trainingfresher.sampleservice.service.project;

import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.model.entity.Section;
import com.trainingfresher.sampleservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
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
    public List<Project> findAllByUserIdAndDepartmentId(Long id) {
        return projectRepository.findAllByUserIdAndDepartmentId(id);
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
}
