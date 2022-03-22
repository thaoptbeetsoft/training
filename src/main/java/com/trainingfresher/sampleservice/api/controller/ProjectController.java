package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Project>>findAllDepartmentId(@PathVariable Long id){
        List<Project> projectIterable = projectService.findAllByDepartmentId(id);
        if(projectIterable.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project>findById(@PathVariable Long id){
        Optional<Project> projectIterable = projectService.findById(id);
        if(!projectIterable.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectIterable.get(), HttpStatus.OK);
    }

    @PostMapping("/{departmentId}")
    public ResponseEntity<Void> create(@PathVariable Long departmentId,@RequestBody Project project){
      Long projectId = projectService.save(project).getId();
       boolean check =  projectService.addProjectInDepartment(departmentId,projectId);
     if(!check){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editProject(@PathVariable Long id, @RequestBody Project project){
        Optional<Project> projectOptional=projectService.findById(id);
        if(!projectOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        project.setId(id);
        projectService.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project>hide(@PathVariable Long id){
        Optional<Project>projectOptional = projectService.findById(id);
        if(!projectOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projectService.hide(id);
        return new ResponseEntity<>(projectOptional.get(), HttpStatus.OK);
    }

}
