package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Project>>findAllDepartmentId(@PathVariable Long _id){
        List<Project> projects = projectService.findAllByDepartmentId(_id);
        if(projects.isEmpty() || projects == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project>findById(@PathVariable Long _id){
        Optional<Project> projectIterable = projectService.findById(_id);
        if(!projectIterable.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projectIterable.get(), HttpStatus.OK);
    }

    @PostMapping("/{departmentId}")
    public ResponseEntity<Void> create(@PathVariable Long _departmentId,@RequestBody Project _project){
      Long projectId = projectService.save(_project).getId();
       boolean check =  projectService.addProjectInDepartment(_departmentId,projectId);
     if(!check){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editProject(@PathVariable Long _id, @RequestBody Project _project){
        Optional<Project> projectOptional=projectService.findById(_id);
        if(!projectOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        _project.setId(_id);
        projectService.save(_project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project>hide(@PathVariable Long _id){
        Optional<Project>projectOptional = projectService.findById(_id);
        if(!projectOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projectService.hide(_id);
        return new ResponseEntity<>(projectOptional.get(), HttpStatus.OK);
    }

}
