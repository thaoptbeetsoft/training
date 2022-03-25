package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.api.form.ProjectForm;
import com.trainingfresher.sampleservice.model.dto.ProjectDto;
import com.trainingfresher.sampleservice.model.entity.Project;
import com.trainingfresher.sampleservice.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping("/department/{id}")
    public ResponseEntity<List<ProjectDto>>findAllDepartmentId(@PathVariable("id") Long _id){
        List<Project> projects = projectService.findAllByDepartmentId(_id);
        if(projects.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProjectDto> projectDtos = projects.stream()
                .map(p ->projectService.convertProjectToDto(p))
                .collect(Collectors.toList());
        return new ResponseEntity<>(projectDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto>findById(@PathVariable("id") Long _id){
        Optional<Project> projectIterable = projectService.findById(_id);
        if(!projectIterable.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProjectDto projectDto = projectService.convertProjectToDto(projectIterable.get());
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody @Valid ProjectForm _projectForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR các trường nhập vào không hợp lệ:\n" +
                            " + Name có độ dài từ 4 đến 30 kí tự \n" +
                            " + Enable chỉ là true/false\n" +
                            " + Department là số tự nhiên lớn hơn 0");
        }
        Project project = projectService.convertFormToProject(_projectForm);
        Long departmentId = _projectForm.getDepartmentId();
         boolean check =  projectService.addProjectInDepartment(departmentId,project);
     if(!check){
         return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body("ERROR NOT FOUND: departmentId");
     }
        return new ResponseEntity<>(HttpStatus.CREATED);
        }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editProject(
            @PathVariable("id") Long _id,
            @RequestBody @Valid ProjectForm _projectForm,
            BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR các trường nhập vào không hợp lệ:\n" +
                            " + Name có độ dài từ 4 đến 30 kí tự \n" +
                            " + Enable chỉ là true/false\n" );
        }
        Optional<Project> projectOptional=projectService.findById(_id);
        if(!projectOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR NOT FOUND: departmentId");
        }
        Project project = projectService.convertFormToProject(_projectForm);
        project.setId(_id);
        project.setName(_projectForm.getName());
        project.setEnable(_projectForm.getEnable());
        projectService.save(project);
        return ResponseEntity.status(HttpStatus.OK).body("Edit OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project>hide(@PathVariable("id") Long _id){
        Optional<Project>projectOptional = projectService.findById(_id);
        if(!projectOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projectService.hide(_id);
        return new ResponseEntity<>(projectOptional.get(), HttpStatus.OK);
    }

}
